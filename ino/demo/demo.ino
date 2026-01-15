
#include <Arduino.h>

// Ardwloop 0.3.9
#include <Ardwloop.h>

// FastLED 3.10.3
#include <FastLED.h>

const int trigPin = 9;
const int echoPin = 10;

#define NUM_LEDS 60

#define DATA_PIN 3

CRGB leds[NUM_LEDS];
uint8_t pos = 0;
bool toggle = false;

int play = 0;

void setup() {
    pinMode(trigPin, OUTPUT);
    pinMode(echoPin, INPUT);

    FastLED.addLeds<WS2812B, DATA_PIN, GRB>(leds, NUM_LEDS);
    FastLED.setBrightness(20);

    pinMode(5, INPUT_PULLUP);
    pinMode(LED_BUILTIN, OUTPUT);
    digitalWrite(LED_BUILTIN, HIGH);

    // Make the led blink on start up
    for (int i = 0; i < 8; i++) {
        digitalWrite(LED_BUILTIN, HIGH);
        delay(99);
        digitalWrite(LED_BUILTIN, LOW);
        delay(99);
    }

    ardw_setup(BAUD_9600);
}

int i = 0;
int last_v = -1;

int bx = 0;

void loop() {

    if (play == 2) {
        playRibbon();
        return;
    }

    if (play == 1) {
        ardw_loop();
        play = 2;
        return;
    }

    ardw_loop();

    digitalWrite(trigPin, LOW);
    delayMicroseconds(2);
    digitalWrite(trigPin, HIGH);
    delayMicroseconds(10);
    digitalWrite(trigPin, LOW);

    float duration = pulseIn(echoPin, HIGH);
    float distance = (duration * .0343) / 2;

    for (int j = 0; j < 12; j++) {
        if (j * 3 < distance * 2)
            leds[j] = CRGB::Yellow;
        else
            leds[j] = CRGB::White;
    }
    FastLED.show();

    int v = ardw_r()->a.v;

    if (v == 1) {
        digitalWrite(LED_BUILTIN, HIGH);
        if (v != last_v) {
            i++;
        }
    } else {
        digitalWrite(LED_BUILTIN, LOW);
    }

    if (digitalRead(5)) {
        ardw_s()->a.w = 1;
    } else {
        ardw_s()->a.w = 2;
        play = 1;
    }

    ardw_s()->a.x = i;
    ardw_s()->a.x = i;

    last_v = v;

    ardw_s()->a.y = 2025;
    ardw_s()->b.x = bx++;

    if (distance < 20) {
        ardw_s()->d.x = distance * 10;
    } else {
        ardw_s()->d.x = -1;
    }

    delay(99);
}

void playRibbon() {
    FastLED.setBrightness(255);
    // Add a bright pixel that moves
    leds[pos] = CHSV(pos * 2, 255, 255);
    // Blur the entire strip
    blur1d(leds, NUM_LEDS, 172);
    fadeToBlackBy(leds, NUM_LEDS, 16);
    FastLED.show();
    // Move the position of the dot
    if (toggle) {
        pos = (pos + 1) % NUM_LEDS;
    }
    toggle = !toggle;
    delay(8);
}
