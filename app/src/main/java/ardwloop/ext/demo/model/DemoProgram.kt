package ardwloop.ext.demo.model

import org.llschall.ardwloop.IArdwProgram
import org.llschall.ardwloop.structure.data.LoopData
import org.llschall.ardwloop.structure.data.SetupData

class DemoProgram : IArdwProgram {
    override fun ardwSetup(s: SetupData?): SetupData {
        TODO("Not yet implemented")
    }

    override fun ardwLoop(s: LoopData?): LoopData {
        TODO("Not yet implemented")
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