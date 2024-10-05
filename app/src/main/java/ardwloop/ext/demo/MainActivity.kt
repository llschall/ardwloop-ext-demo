package ardwloop.ext.demo

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import ardwloop.ext.demo.model.BluetoothHandler
import ardwloop.ext.demo.model.LogsModel
import ardwloop.ext.demo.ui.theme.ArdwloopTheme
import org.llschall.ardwloop.ArdwloopStarter
import org.llschall.ardwloop.ext.ArdwloopExtStarter
import org.llschall.ardwloop.structure.utils.Logger

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
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
    val model: LogsModel = viewModel()

    Row {
        Column {
            Text(
                text = "Hello $name !",
                modifier = modifier,
                fontSize = 12.sp
            )
            Text(
                text = "ardwloop " + ArdwloopStarter.VERSION,
                modifier = modifier,
                fontSize = 12.sp
            )
            Text(
                text = "ardwloop-ext " + ArdwloopExtStarter().VERSION,
                modifier = modifier, fontSize = 12.sp
            )
            Button(onClick = {
                model.msg("=====================")
            }) {
                Text(
                    text = "Print",
                    fontSize = 28.sp
                )
            }
            Spacer(modifier)
            Button(onClick = {
                BluetoothHandler.handler.connect(context, model);
            }) {
                Text(
                    text = "Connect",
                    fontSize = 28.sp
                )
            }
            Spacer(modifier)
            Button(onClick = {
                val bytes = BluetoothHandler.handler.read();
                model.addBytes(bytes)
                model.msg(bytes.toString())
                model.msg(bytes.size.toString() + " bytes read")
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
                BluetoothHandler.handler.close(model)
            }) {
                Text(
                    text = "Close",
                    fontSize = 28.sp
                )
            }
            Spacer(modifier)
            Button(onClick = {
                BluetoothHandler.handler.demo(model)
            }) {
                Text(
                    text = "Demo",
                    fontSize = 28.sp
                )
            }
            Button(onClick = {
                BluetoothHandler.handler.program.v = 0
            }) {
                Text(
                    text = "Off",
                    fontSize = 28.sp
                )
            }
            Button(onClick = {
                BluetoothHandler.handler.program.v = 1
            }) {
                Text(
                    text = "On",
                    fontSize = 28.sp
                )
            }
        }
        Column {
            for (text in model.logs) {
                Text(
                    text = text
                )
            }
        }
        Column {
            Text(
                text = model.dumpBytes()
            )
        }
    }
}