package com.litejvm.instructions.conversions.d2x;

import com.litejvm.instructions.base.NoOperandsInstruction;
import com.litejvm.rtdata.Frame;
import com.litejvm.rtdata.OperandStack;

public class D2L extends NoOperandsInstruction {
    @Override
    public void execute(Frame frame) {
        OperandStack operandStack = frame.getOperandStack();
        double d = operandStack.popDouble();
        operandStack.pushLong((long) d);
    }
}
