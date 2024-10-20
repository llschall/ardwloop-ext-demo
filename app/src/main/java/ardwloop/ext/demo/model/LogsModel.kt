package ardwloop.ext.demo.model

import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import java.io.StringWriter
import java.util.Date

class LogsModel() : ViewModel() {

    val logs = List(2) { "*" }.toMutableStateList()

    val bytes = List(2) { (99).toByte() }.toMutableStateList()

    val status = List(1) { "not started" }.toMutableStateList()

    fun msg(msg: String) {
        if (logs.size == 27) logs.remove(logs.first())
        logs.add(Date().time.toString() + " µµµ " + msg)
    }

    fun addBytes(bytes: ByteArray) {
        bytes.forEach { this.bytes.add(it) }
    }

    fun dumpBytes(): String {

        val writer = StringWriter()
        for (byte in bytes) {
            writer.append(byte.toInt().toChar())
        }
        return writer.toString()
    }
}