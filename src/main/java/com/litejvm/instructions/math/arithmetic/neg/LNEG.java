package com.litejvm.instructions.math.arithmetic.neg;

import com.litejvm.instructions.base.NoOperandsInstruction;
import com.litejvm.rtdata.Frame;
import com.litejvm.rtdata.OperandStack;

public class LNEG extends NoOperandsInstruction {
    @Override
    public void execute(Frame frame) {
        OperandStack operandStack = frame.getOperandStack();
        long val = operandStack.popLong();
        operandStack.pushLong(val);
    }
}
