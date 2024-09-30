package ardwloop.ext.demo;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import java.util.Objects;

public class BuildTest {

    @Test
    public void checkJava() {
        assertEquals("17",
                Objects.requireNonNull(System.getProperty("java.version")).split("\\.")[0]);
    }
}
