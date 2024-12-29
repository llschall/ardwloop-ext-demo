package ardwloop.ext.demo.ui

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import ardwloop.ext.demo.model.BluetoothHandler

@Preview
@Composable
fun DeviceListView(
    list: List<String> = listOf("dev0", "dev1", "dev2")
) {

    val device = BluetoothHandler.handler.logs.device.value
    Row(Modifier.selectableGroup()) {
        list.forEach { text ->
            Row {
                RadioButton(
                    selected = (text == device),
                    onClick = {
                        BluetoothHandler.handler.logs.device.value = text;
                        BluetoothHandler.handler.logs.msg("Selected $text")
                    }
                )
                Text(
                    text = text,
                )
            }
        }
    }
}