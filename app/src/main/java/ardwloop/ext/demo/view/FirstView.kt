package ardwloop.ext.demo.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ardwloop.ext.demo.model.BluetoothHandler

@Preview
@Composable
fun FirstView() {
    val distance = BluetoothHandler.handler.logs.distance.doubleValue
    Column (
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFE0F7FA)),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Measured distance is",
            fontSize = 50.sp,
            color = Color(0xFF01579B)
        )
        Spacer(modifier = Modifier.height(32.dp))
        Text(
            text = "$distance cm",
            fontWeight = FontWeight.Bold,
            fontSize = 100.sp,
            color = Color(0xFF01579B)
        )
    }
}
