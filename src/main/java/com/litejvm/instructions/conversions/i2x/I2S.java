package com.litejvm.instructions.conversions.i2x;

import com.litejvm.instructions.base.NoOperandsInstruction;
import com.litejvm.rtdata.Frame;
import com.litejvm.rtdata.OperandStack;

public class I2S extends NoOperandsInstruction {
    @Override
    public void execute(Frame frame) {
        OperandStack operandStack = frame.getOperandStack();
        int val = operandStack.popInt();
        short s = (short) val;
        operandStack.pushInt(s);
    }
}
