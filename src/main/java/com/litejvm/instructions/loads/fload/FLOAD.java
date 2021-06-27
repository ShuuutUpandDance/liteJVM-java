package com.litejvm.instructions.loads.fload;

import com.litejvm.instructions.base.Index8Instruction;
import com.litejvm.rtdata.Frame;

public class FLOAD extends Index8Instruction implements FLOADInstruction{
    @Override
    public void execute(Frame frame) {
        fload(frame, index);
    }
}
