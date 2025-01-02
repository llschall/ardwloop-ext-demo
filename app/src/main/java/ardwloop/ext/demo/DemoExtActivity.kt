package ardwloop.ext.demo

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import ardwloop.ext.demo.model.BluetoothHandler
import ardwloop.ext.demo.view.LeftColumn
import ardwloop.ext.demo.view.theme.ArdwloopTheme

class DemoExtActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ArdwloopTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    DemoExt(
                        context = applicationContext,
                        paddingValues = innerPadding
                    )
                }
            }
        }
    }
}

@Composable
fun DemoExt(
    context: Context,
    paddingValues: PaddingValues
) {
    val modifier = Modifier
        .padding(paddingValues)
        .padding(Dp(3f))

    val version = context.packageManager.getPackageInfo(context.packageName, 0)

    Row {
        LeftColumn(modifier, version.versionName!!,
            listFct = {
                BluetoothHandler.handler.listDevicesExc(context)
            },
            connectFct = {
                BluetoothHandler.handler.connectExc(context)
            }
        )
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

