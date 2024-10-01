package ardwloop.ext.demo.model

import android.Manifest
import android.bluetooth.BluetoothManager
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import java.util.UUID

//See https://developer.android.com/reference/android/bluetooth/BluetoothDevice
const val SPP_UUID = "00001101-0000-1000-8000-00805F9B34FB"

class BluetoothHandler {
    companion object {
        val handler: BluetoothHandler = BluetoothHandler()
    }

    fun start(context: Context, logs: LogsModel) {
        logs.add("handler")
        val manager = context.getSystemService(BluetoothManager::class.java)
        logs.add("enabled: " + manager.adapter.isEnabled)
        if (ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.BLUETOOTH_CONNECT
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            for (device in manager.adapter.bondedDevices) {
                logs.add("name: " + device.name)
                if (device.name == "HC05") {
                    logs.add("=== Found HC05 ===")

                    val uuid = UUID.fromString(SPP_UUID)
                    device.createRfcommSocketToServiceRecord(uuid)
                    manager.adapter.cancelDiscovery()
                }
            }
        }

    }

}