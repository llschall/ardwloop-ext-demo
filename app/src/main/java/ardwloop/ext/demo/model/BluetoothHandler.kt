package ardwloop.ext.demo.model

import android.Manifest
import android.bluetooth.BluetoothManager
import android.bluetooth.BluetoothSocket
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

    private var socket: BluetoothSocket? = null

    fun connect(context: Context, logs: LogsModel) {
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
                    socket = device.createRfcommSocketToServiceRecord(uuid)
                    socket!!.connect()
                    logs.add("" + socket!!.connectionType)
                    logs.add("" + socket!!.maxReceivePacketSize)
                    logs.add("" + socket!!.maxTransmitPacketSize)
                }
            }
            manager.adapter.cancelDiscovery()
            logs.add("finished")
        }
    }

    fun close(logs: LogsModel) {
        logs.add("close")
        socket?.close()
        logs.add("finished")
    }

    fun write(b: ByteArray) {
        socket!!.outputStream.write(b)
    }

    fun read(): ByteArray {
        val stream = socket!!.inputStream
        val array = mutableListOf<Byte>()
        var n = stream.available()
        while (n > 0) {
            val b = stream.read()
            array.add(b.toByte())
            n = stream.available()
        }
        return array.toByteArray()
    }
}