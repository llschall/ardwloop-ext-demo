package ardwloop.ext.demo.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import ardwloop.ext.demo.model.BluetoothHandler
import org.llschall.ardwloop.ArdwloopStarter
import org.llschall.ardwloop.ext.ArdwloopExtStarter

@Preview
@Composable
fun LeftColumn(
    modifier: Modifier = Modifier,
    version: String = "0.X.X",
    connectExt: () -> Unit = {},
) {

    Column {
        Text(
            text = "Demo $version",
            modifier = modifier,
            fontSize = 42.sp
        )
        Text(
            text = "ardwloop " + ArdwloopStarter.VERSION + " #" + ArdwloopStarter.VERSION_INT,
            modifier = modifier,
            fontSize = 12.sp
        )
        Text(
            text = "ardwloop-ext " + ArdwloopExtStarter().VERSION + " #" + ArdwloopExtStarter().VERSION_INT,
            modifier = modifier, fontSize = 12.sp
        )
        Button(onClick = {
            BluetoothHandler.handler.print()
        }) {
            Text(
                text = "Print",
                fontSize = 28.sp
            )
        }
        Spacer(modifier)
        Button(onClick = connectExt) {
            Text(
                text = "Connect",
                fontSize = 42.sp
            )
        }
        Spacer(modifier)
        Button(onClick = {
            val bytes = BluetoothHandler.handler.read();
            BluetoothHandler.handler.logs.addBytes(bytes)
            BluetoothHandler.handler.logs.msg(bytes.toString())
            BluetoothHandler.handler.logs.msg(bytes.size.toString() + " bytes read")
        }) {
            Text(
                text = "Read",
                fontSize = 28.sp
            )
        }
        Spacer(modifier)
        Button(onClick = {
            BluetoothHandler.handler.write(byteArrayOf(99, 100));
        }) {
            Text(
                text = "Write",
                fontSize = 28.sp
            )
        }
        Spacer(modifier)
        Button(onClick = {
            BluetoothHandler.handler.close()
        }) {
            Text(
                text = "Close",
                fontSize = 28.sp
            )
        }
        Spacer(modifier)
        Button(
            enabled = BluetoothHandler.handler.logs.demoEnabled.value,
            onClick = {
                BluetoothHandler.handler.demo()
            }) {
            Text(
                text = "Demo",
                fontSize = 42.sp
            )
        }
        Text(
            text = BluetoothHandler.handler.logs.status.first(),
            fontSize = 28.sp
        )
        Spacer(modifier)
        Button(
            enabled = BluetoothHandler.handler.logs.switchEnabled.value,
            onClick = {
                BluetoothHandler.handler.switch()
            }) {
            Text(
                text = "Switch",
                fontSize = 72.sp
            )
        }
        Spacer(modifier)
        Button(onClick = {
            BluetoothHandler.handler.exit()
        }) {
            Text(
                text = "Exit",
                fontSize = 28.sp
            )
        }
    }


}