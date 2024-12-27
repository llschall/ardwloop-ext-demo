package ardwloop.ext.demo.model

import org.llschall.ardwloop.IArdwProgram
import org.llschall.ardwloop.value.SerialData

class DemoProgram(private val logs: LogsModel, val onSetup: Function0<Unit>) : IArdwProgram {

    var v = 0

    override fun ardwSetup(s: SerialData): SerialData {
        logs.msg("== Program Setup OK ==")
        onSetup()
        return SerialData(2, 3, 4, 5, 6)
    }

    override fun ardwLoop(s: SerialData): SerialData {
        return SerialData(v, 3, 4, 5, 6)
    }

    override fun getReadDelayMs(): Int {
        return 99
    }

    override fun getPostDelayMs(): Int {
        return 9999
    }


}