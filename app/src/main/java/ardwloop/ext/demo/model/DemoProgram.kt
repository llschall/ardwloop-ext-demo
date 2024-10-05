package ardwloop.ext.demo.model

import org.llschall.ardwloop.IArdwProgram
import org.llschall.ardwloop.structure.data.LoopData
import org.llschall.ardwloop.structure.data.SerialData
import org.llschall.ardwloop.structure.data.SetupData

class DemoProgram : IArdwProgram {

    var v = 0

    override fun ardwSetup(s: SetupData?): SetupData {
        print("SETUP")
        return SetupData(SerialData(1, 2, 3, 4, 5, 6))
    }

    override fun ardwLoop(s: LoopData?): LoopData {
        print("LOOP")
        return LoopData(SerialData(1, v, 3, 4, 5, 6))
    }

    override fun getRc(): Int {
        return 1
    }

    override fun getSc(): Int {
        return 1
    }

    override fun getReadDelayMs(): Int {
        return 99
    }

    override fun getPostDelayMs(): Int {
        return 9999
    }


}