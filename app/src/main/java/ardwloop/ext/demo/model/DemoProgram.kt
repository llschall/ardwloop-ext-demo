package ardwloop.ext.demo.model

import org.llschall.ardwloop.IArdwProgram
import org.llschall.ardwloop.value.ValueMap

class DemoProgram(private val logs: LogsModel, val onSetup: Function0<Unit>) : IArdwProgram {

    var v = 0

    override fun ardwSetup(s: ValueMap): ValueMap {
        logs.msg("== Program Setup OK ==")
        onSetup()
        return ValueMap(2, 3, 4, 5, 6)
    }

    override fun ardwLoop(s: ValueMap): ValueMap {
        return ValueMap(v, 3, 4, 5, 6)
    }

    override fun getReadDelayMs(): Int {
        return 99
    }

    override fun getPostDelayMs(): Int {
        return 9999
    }


}