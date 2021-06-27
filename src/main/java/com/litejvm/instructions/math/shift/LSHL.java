package com.litejvm.instructions.math.shift;

import com.litejvm.instructions.base.NoOperandsInstruction;
import com.litejvm.rtdata.Frame;
import com.litejvm.rtdata.OperandStack;

public class LSHL extends NoOperandsInstruction {
    @Override
    public void execute(Frame frame) {
        OperandStack operandStack = frame.getOperandStack();
        int v2 = operandStack.popInt();
        long v1 = operandStack.popLong();
        int offset = v2 & 0x3f;
        operandStack.pushLong(v1 << offset);
    }
}
