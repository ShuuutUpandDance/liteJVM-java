package com.litejvm.instructions.stores.lstore;

import com.litejvm.rtdata.Frame;

public interface LSTOREInstruction {
    default void lstore(Frame frame, int index) {
        long val = frame.getOperandStack().popLong();
        frame.getLocalVars().setLong(index, val);
    }
}
