package com.litejvm.instructions.loads.aload;

import com.litejvm.instructions.base.Index8Instruction;
import com.litejvm.rtdata.Frame;

public class ALOAD extends Index8Instruction implements ALOADInstruction {
    @Override
    public void execute(Frame frame) {
        aload(frame, index);
    }
}
