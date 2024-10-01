package ardwloop.ext.demo.model

//See https://developer.android.com/reference/android/bluetooth/BluetoothDevice
const val SPP_UUID = "00001101-0000-1000-8000-00805F9B34FB"

class BluetoothHandler {
    companion object {
        val handler: BluetoothHandler = BluetoothHandler()
    }

    fun start(logs: LogsModel) {
        logs.add("handler")
    }

}