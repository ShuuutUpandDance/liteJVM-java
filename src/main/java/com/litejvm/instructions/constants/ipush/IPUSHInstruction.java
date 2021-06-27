package com.litejvm.instructions.constants.ipush;

import com.litejvm.instructions.base.Instruction;
import com.litejvm.rtdata.Frame;

public abstract class IPUSHInstruction implements Instruction {
    int val;

    @Override
    public void execute(Frame frame) {
        frame.getOperandStack().pushInt(val);
    }

    @Override
    public String toString() {
        return "IPUSHInstruction{" +
                "val=" + val +
                '}';
    }
}
