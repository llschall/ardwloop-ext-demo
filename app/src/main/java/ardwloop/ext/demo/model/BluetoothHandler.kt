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
import org.llschall.ardwloop.IArdwConfig
import org.llschall.ardwloop.ext.ArdwloopExtStarter
import org.llschall.ardwloop.structure.StructureTimer

class BluetoothHandler {
    companion object {
        val handler: BluetoothHandler = BluetoothHandler()
    }

    private var socket: BluetoothSocket? = null

    val logs = LogsModel()

    fun listDevicesExc(context: Context) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                listDevices(context = context)
                val connected = socket!!.isConnected
                logs.msg("Connected ? $connected")
                logs.demoEnabled.value = true
            } catch (error: Throwable) {
                logs.err(error)
            }
        }
    }

    private fun listDevices(context: Context) {
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

        logs.msg("List devices...")
        logs.devices.clear()
        for (device in manager.adapter.bondedDevices) {
            logs.msg("Found: " + device.name)
            logs.devices.add(device.name)
        }
        logs.device.value = logs.devices[0]
        logs.msg("Finished.")
    }

    fun connectExc(context: Context) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                connect(context = context)
                val connected = socket!!.isConnected
                logs.msg("Connected ? $connected")
                logs.demoEnabled.value = true
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
        }

        val name = logs.device.value

        logs.msg("Trying to connect to $name")
        for (device in manager.adapter.bondedDevices) {
            logs.msg("Found: " + device.name)
            if (device.name == name) {
                logs.msg("=== Found $name ===")
                val uuid = ArdwloopExtStarter().SPP_UUID
                socket = device.createRfcommSocketToServiceRecord(uuid)
                socket!!.connect()
                logs.msg(
                    "" + socket!!.connectionType
                            + "&" + socket!!.maxReceivePacketSize
                            + "&" + socket!!.maxTransmitPacketSize
                )
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

    private val program = DemoProgram(logs)

    fun print() {
        logs.dump()
    }

    fun demo() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val starter = ArdwloopExtStarter()
                starter.start(program, IArdwConfig.BAUD_9600, socket!!, "")
                logs.msg("Demo started.")
                logs.status.value = "OFF"
            } catch (e: Exception) {
                logs.msg("ERR: " + e.message.toString())
            }
        }
        logs.msg("Demo starting...")
    }

    fun switch() {
        if (handler.program.v == 0) {
            handler.program.v = 1
            logs.status.value = "ON"
        } else {
            handler.program.v = 0
            logs.status.value = "OFF"
        }
    }

    fun exit() {
        StructureTimer.get().shutdown()
    }


}