package ardwloop.ext.demo.model

import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import java.util.Date

class LogsModel() : ViewModel() {
    val logs = List(2) { "*" }.toMutableStateList()

    fun add() {
        if (logs.size == 16) logs.remove(logs.first())
        logs.add(Date().time.toString() + " µµµ")
    }
}