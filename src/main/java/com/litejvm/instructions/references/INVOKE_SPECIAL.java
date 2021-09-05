package com.litejvm.instructions.references;

import com.litejvm.instructions.base.Index16Instruction;
import com.litejvm.rtdata.Frame;

public class INVOKE_SPECIAL extends Index16Instruction {
    @Override
    public void execute(Frame frame) {
        frame.operandStack.popRef();
    }
}
