package ardwloop.ext.demo.view

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import ardwloop.ext.demo.model.BluetoothHandler

@Preview
@Composable
fun PageView() {
    if (BluetoothHandler.handler.logs.firstScreen.value)
        FirstView()
    else
        SecondView()
}