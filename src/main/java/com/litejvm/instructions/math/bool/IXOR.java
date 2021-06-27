package com.litejvm.instructions.math.bool;

import com.litejvm.instructions.base.NoOperandsInstruction;
import com.litejvm.rtdata.Frame;
import com.litejvm.rtdata.OperandStack;

public class IXOR extends NoOperandsInstruction {
    @Override
    public void execute(Frame frame) {
        OperandStack operandStack = frame.getOperandStack();
        int val2 = operandStack.popInt();
        int val1 = operandStack.popInt();
        operandStack.pushInt(val1 ^ val2);
    }
}
