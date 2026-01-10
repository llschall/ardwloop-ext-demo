package ardwloop.ext.demo.model

import org.llschall.ardwloop.IArdwProgram
import org.llschall.ardwloop.value.SerialData

class DemoProgram(private val logs: LogsModel) : IArdwProgram {

    var builtInLed = 0

    override fun ardwSetup(s: SerialData): SerialData {
        logs.msg("== Program Setup OK ==")
        logs.switchEnabled.value = true
        return SerialData()
    }

    override fun ardwLoop(s: SerialData): SerialData {
        logs.firstScreen.value = s.a.w == 1
        return SerialData(builtInLed)
    }

    override fun getReadDelayMs(): Int {
        return 99
    }

    override fun getPostDelayMs(): Int {
        return 9999
    }


}