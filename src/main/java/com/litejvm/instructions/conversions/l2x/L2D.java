package com.litejvm.instructions.conversions.l2x;

import com.litejvm.instructions.base.NoOperandsInstruction;
import com.litejvm.rtdata.Frame;
import com.litejvm.rtdata.OperandStack;

public class L2D extends NoOperandsInstruction {
    @Override
    public void execute(Frame frame) {
        OperandStack operandStack = frame.getOperandStack();
        long l = operandStack.popLong();
        operandStack.pushDouble(l);
    }
}
