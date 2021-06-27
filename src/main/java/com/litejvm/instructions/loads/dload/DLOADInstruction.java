package com.litejvm.instructions.loads.dload;

import com.litejvm.rtdata.Frame;

public interface DLOADInstruction {
    default void dload(Frame frame, int index) {
        double val = frame.getLocalVars().getDouble(index);
        frame.getOperandStack().pushDouble(val);
    }
}
