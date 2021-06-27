package com.litejvm.instructions.loads.aload;

import com.litejvm.instructions.base.NoOperandsInstruction;
import com.litejvm.rtdata.Frame;

public class ALOAD_3 extends NoOperandsInstruction implements ALOADInstruction {
    @Override
    public void execute(Frame frame) {
        aload(frame, 3);
    }
}
