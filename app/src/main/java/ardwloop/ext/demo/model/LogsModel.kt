package ardwloop.ext.demo.model

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import org.llschall.ardwloop.ArdwloopStarter
import org.llschall.ardwloop.ext.ArdwloopExtStarter
import java.util.Date

class LogsModel() : ViewModel() {

    val logs = List(1) { "Welcome !" }.toMutableStateList()

    val bytes = List(2) { (99).toByte() }.toMutableStateList()

    val devices = List(1) { "device?" }.toMutableStateList()
    val device = mutableStateOf("")

    val status = mutableStateOf("not started")
    var demoEnabled = mutableStateOf(false)
    var switchEnabled = mutableStateOf(false)
    
    var firstScreen = mutableStateOf(true)

    fun dump() {
        msg("ArdwloopExt:" + ArdwloopExtStarter().VERSION + " #" + ArdwloopExtStarter().VERSION_INT)
        msg("Ardwloop: " + ArdwloopStarter.VERSION + " #" + ArdwloopStarter.VERSION_INT)
        msg(
            "Runtime: " + Runtime.getRuntime()
                .availableProcessors() + "&" + Runtime.getRuntime()
                .freeMemory() / 1024 + "&" + Runtime.getRuntime().maxMemory() / 1024
        )
    }

    fun msg(msg: String) {
        if (logs.size == 27) logs.remove(logs.first())
        logs.add(Date().time.toString() + " " + msg)
    }

    fun err(error: Throwable) {
        logs.add("==================")
        logs.add(error.message.toString())
        logs.add("==================")
        error.stackTrace.forEach { logs.add(it.toString()) }
        logs.add("==================")
    }

    fun addBytes(bytes: ByteArray) {
        bytes.forEach { this.bytes.add(it) }
    }
}