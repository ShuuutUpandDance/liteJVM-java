package com.litejvm.instructions.stores.istore;

import com.litejvm.rtdata.Frame;

public interface ISTOREInstruction {
    default void istore(Frame frame, int index) {
        int val = frame.getOperandStack().popInt();
        frame.getLocalVars().setInt(index, val);
    }
}
