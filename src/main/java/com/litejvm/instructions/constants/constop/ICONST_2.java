package com.litejvm.instructions.constants.constop;

import com.litejvm.instructions.base.NoOperandsInstruction;
import com.litejvm.rtdata.Frame;

public class ICONST_2 extends NoOperandsInstruction {
    @Override
    public void execute(Frame frame) {
        frame.getOperandStack().pushInt(2);
    }
}
