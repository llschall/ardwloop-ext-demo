package ardwloop.ext.demo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import ardwloop.ext.demo.model.BluetoothHandler
import ardwloop.ext.demo.model.LogsModel
import ardwloop.ext.demo.ui.theme.ArdwloopTheme
import org.llschall.ardwloop.ArdwloopStarter
import org.llschall.ardwloop.ext.ArdwloopExtStarter

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ArdwloopTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Ardwloop Ext Demo",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {

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
                model.add("start")
            }) {
                Text(
                    text = "Start",
                    fontSize = 28.sp
                )
            }
            Button(onClick = {
                BluetoothHandler.handler.start(model);
            }) {
                Text(
                    text = "Handler",
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
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ArdwloopTheme {
        Greeting("Android")
    }
}
