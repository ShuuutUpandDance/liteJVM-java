package com.litejvm.instructions.math.arithmetic.neg;

import com.litejvm.instructions.base.NoOperandsInstruction;
import com.litejvm.rtdata.Frame;
import com.litejvm.rtdata.OperandStack;

public class DNEG extends NoOperandsInstruction {
    @Override
    public void execute(Frame frame) {
        OperandStack operandStack = frame.getOperandStack();
        double val = operandStack.popDouble();
        operandStack.pushDouble(val);
    }
}
