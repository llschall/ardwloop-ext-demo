package ardwloop.ext.demo.model

import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import java.util.Date

class LogsModel() : ViewModel() {
    val logs = List(2) { "*" }.toMutableStateList()

    fun add(msg: String) {
        if (logs.size == 27) logs.remove(logs.first())
        logs.add(Date().time.toString() + " µµµ " + msg)
    }
}