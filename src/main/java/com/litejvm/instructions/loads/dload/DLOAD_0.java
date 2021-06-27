package com.litejvm.instructions.loads.dload;

import com.litejvm.instructions.base.NoOperandsInstruction;
import com.litejvm.rtdata.Frame;

public class DLOAD_0 extends NoOperandsInstruction implements DLOADInstruction {
    @Override
    public void execute(Frame frame) {
        dload(frame, 0);
    }
}
