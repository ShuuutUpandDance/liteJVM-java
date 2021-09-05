package com.litejvm.instructions.constants.ldc;

import com.litejvm.instructions.base.Index16Instruction;
import com.litejvm.rtdata.Frame;

public class LDC_W extends Index16Instruction implements LDCInstruction {
    @Override
    public void execute(Frame frame) {
        ldc(frame, index);
    }
}
