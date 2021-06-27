package com.litejvm.instructions.loads.iload;

import com.litejvm.rtdata.Frame;

public interface ILOADInstruction {
    default void iload(Frame frame, int index) {
        int val = frame.getLocalVars().getInt(index);
        frame.getOperandStack().pushInt(val);
    }
}
