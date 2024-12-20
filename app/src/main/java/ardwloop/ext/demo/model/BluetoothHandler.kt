package ardwloop.ext.demo.model

import android.Manifest
import android.bluetooth.BluetoothManager
import android.bluetooth.BluetoothSocket
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.llschall.ardwloop.ext.ArdwloopExtStarter
import org.llschall.ardwloop.structure.StructureTimer
import java.util.UUID

//See https://developer.android.com/reference/android/bluetooth/BluetoothDevice
const val SPP_UUID = "00001101-0000-1000-8000-00805F9B34FB"

class BluetoothHandler {
    companion object {
        val handler: BluetoothHandler = BluetoothHandler()
    }

    var switchEnabled = false

    private var socket: BluetoothSocket? = null

    val logs = LogsModel()

    fun connectExc(context: Context) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                connect(context = context)
                logs.msg("Connected ? " + socket!!.isConnected)
            } catch (error: Throwable) {
                logs.err(error)
            }
        }
        logs.msg("Connect...")
    }

    private fun connect(context: Context) {
        val manager = context.getSystemService(BluetoothManager::class.java)
        logs.msg("Enabled: " + manager.adapter.isEnabled)
        if (ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.BLUETOOTH_CONNECT
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            logs.msg("BLUETOOTH_CONNECT granted.")
        } else {
            logs.msg("BLUETOOTH_CONNECT not granted.")

            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
        }

        logs.msg("Trying to connect...: ")
        for (device in manager.adapter.bondedDevices) {
            logs.msg("name: " + device.name)
            if (device.name == "HC05") {
                logs.msg("=== Found HC05 ===")
                val uuid = UUID.fromString(SPP_UUID)
                socket = device.createRfcommSocketToServiceRecord(uuid)
                socket!!.connect()
                logs.msg(
                    "" + socket!!.connectionType
                            + "&" + socket!!.maxReceivePacketSize
                            + "&" + socket!!.maxTransmitPacketSize
                )
                demoEnabled = socket!!.isConnected
            }
        }
        logs.msg("Finished.")
    }

    fun close() {
        logs.msg("close")
        socket?.close()
        logs.msg("finished")
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

    private val program = DemoProgram(logs) { switchEnabled = true }

    fun print() {
        logs.dump()
    }

    var demoEnabled = false

    fun demo() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val starter = ArdwloopExtStarter()
                starter.start(program, 9600, socket!!, "HC05")
                logs.msg("Demo started.")
                logs.status[0] = "OFF"
            } catch (e: Exception) {
                logs.msg("ERR: " + e.message.toString())
            }
        }
        logs.msg("Demo starting...")
    }

    fun switch() {
        if (handler.program.v == 0) {
            handler.program.v = 1
            logs.status[0] = "ON"
        } else {
            handler.program.v = 0
            logs.status[0] = "OFF"
        }
    }

    fun exit() {
        StructureTimer.get().shutdown()
    }


}