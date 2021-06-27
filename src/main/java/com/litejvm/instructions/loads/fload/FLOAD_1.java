package com.litejvm.instructions.loads.fload;

import com.litejvm.instructions.base.NoOperandsInstruction;
import com.litejvm.rtdata.Frame;

public class FLOAD_1 extends NoOperandsInstruction implements FLOADInstruction {
    @Override
    public void execute(Frame frame) {
        fload(frame, 1);
    }
}
