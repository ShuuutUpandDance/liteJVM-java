package com.litejvm.instructions.stores.dstore;

import com.litejvm.rtdata.Frame;

public interface DSTOREInstruction {
    default void dstore(Frame frame, int index) {
        double val = frame.getOperandStack().popDouble();
        frame.getLocalVars().setDouble(index, val);
    }
}
