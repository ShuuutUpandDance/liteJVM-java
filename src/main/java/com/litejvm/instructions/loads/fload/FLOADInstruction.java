package com.litejvm.instructions.loads.fload;

import com.litejvm.rtdata.Frame;

public interface FLOADInstruction {
    default void fload(Frame frame, int index) {
        float val = frame.getLocalVars().getFloat(index);
        frame.getOperandStack().pushFloat(val);
    }
}
