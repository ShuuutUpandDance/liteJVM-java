package com.litejvm.instructions.conversions.d2x;

import com.litejvm.instructions.base.NoOperandsInstruction;
import com.litejvm.rtdata.Frame;
import com.litejvm.rtdata.OperandStack;

public class D2F extends NoOperandsInstruction {
    @Override
    public void execute(Frame frame) {
        OperandStack operandStack = frame.getOperandStack();
        double d = operandStack.popDouble();
        operandStack.pushFloat((float) d);
    }
}
