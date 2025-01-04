package ardwloop.ext.demo.view

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp

@Preview
@Composable
fun PageView() {
    Column {
        Text(
            text = "Another Screen",
            fontSize = 64.sp
        )
    }
}