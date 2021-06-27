package com.litejvm.instructions.stores.fstore;

import com.litejvm.rtdata.Frame;

public interface FSTOREInstruction {
    default void fstore(Frame frame, int index) {
        float val = frame.getOperandStack().popFloat();
        frame.getLocalVars().setFloat(index, val);
    }
}
