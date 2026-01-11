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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Preview
@Composable
fun SecondView() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFE0F7FA)),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        val icon = "\uD83C\uDF89"
        Text(
            text = "Happy new year",
            fontSize = 64.sp,
            textAlign = TextAlign.Center,
            color = Color(0xFF01579B)
        )
        Spacer(modifier = Modifier.height(32.dp))
        Text(
            text = "2026",
            fontSize = 120.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF01579B)
        )
        Spacer(modifier = Modifier.height(50.dp))
        Text(
            text = "$icon",
            fontSize = 160.sp,
            textAlign = TextAlign.Center,
            color = Color(0xFF01579B)
        )
    }
}
