package com.litejvm.instructions.conversions.f2x;

import com.litejvm.instructions.base.NoOperandsInstruction;
import com.litejvm.rtdata.Frame;
import com.litejvm.rtdata.OperandStack;

public class F2I extends NoOperandsInstruction {
    @Override
    public void execute(Frame frame) {
        OperandStack operandStack = frame.getOperandStack();
        float val = operandStack.popFloat();
        operandStack.pushInt((int) val);
    }
}
