package com.litejvm.instructions.loads.lload;

import com.litejvm.rtdata.Frame;

public interface LLOADInstruction {
    default void lload(Frame frame, int index) {
        long val = frame.getLocalVars().getLong(index);
        frame.getOperandStack().pushLong(val);
    }
}
