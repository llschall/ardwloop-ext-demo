package ardwloop.ext.demo.view

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp

@Preview(
    showBackground = true
)
@Composable
fun FirstView() {
    Column {
        Text(
            text = "First Screen",
            fontSize = 64.sp
        )
        Text(
            text = "Appears when the switch is the first position.",
            fontSize = 16.sp
        )
    }
}