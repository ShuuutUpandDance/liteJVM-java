package com.litejvm.instructions.constants.ldc;

import com.litejvm.instructions.base.Index8Instruction;
import com.litejvm.rtdata.Frame;

public class LDC extends Index8Instruction implements LDCInstruction {
    @Override
    public void execute(Frame frame) {
        ldc(frame, index);
    }
}
