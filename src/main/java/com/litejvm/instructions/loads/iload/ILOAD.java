package com.litejvm.instructions.loads.iload;

import com.litejvm.instructions.base.Index8Instruction;
import com.litejvm.rtdata.Frame;

public class ILOAD extends Index8Instruction implements ILOADInstruction {

    @Override
    public void execute(Frame frame) {
        iload(frame, index);
    }
}
