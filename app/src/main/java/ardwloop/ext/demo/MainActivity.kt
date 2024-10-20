package ardwloop.ext.demo

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.sp
import ardwloop.ext.demo.model.BluetoothHandler
import ardwloop.ext.demo.ui.theme.ArdwloopTheme
import org.llschall.ardwloop.ArdwloopStarter
import org.llschall.ardwloop.ext.ArdwloopExtStarter
import org.llschall.ardwloop.structure.utils.Logger

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ArdwloopTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        context = applicationContext,
                        name = "Ardwloop Ext Demo",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(context: Context, name: String, modifier: Modifier = Modifier) {

    Logger.skipMsg = true
    val modifier = Modifier.padding(Dp(3f))

    Row {
        Column {
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
            Button(onClick = {
                BluetoothHandler.handler.connectExc(context);
            }) {
                Text(
                    text = "Connect",
                    fontSize = 28.sp
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
            Button(onClick = {
                BluetoothHandler.handler.demo()
            }) {
                Text(
                    text = "Demo",
                    fontSize = 28.sp
                )
            }
            Text(
                text = BluetoothHandler.handler.logs.status.first(),
                fontSize = 28.sp
            )
            Spacer(modifier)
            Button(onClick = {
                BluetoothHandler.handler.switch()
            }) {
                Text(
                    text = "Switch",
                    fontSize = 64.sp
                )
            }
        }
        Column(
            modifier = Modifier.verticalScroll(
                rememberScrollState()
            )
        ) {
            for (text in BluetoothHandler.handler.logs.logs) {
                Text(
                    text = text
                )
            }
        }
    }
}