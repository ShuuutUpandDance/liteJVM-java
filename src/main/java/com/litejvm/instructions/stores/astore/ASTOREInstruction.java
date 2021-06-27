package com.litejvm.instructions.stores.astore;

import com.litejvm.rtdata.Frame;
import com.litejvm.rtdata.Object;

public interface ASTOREInstruction {
    default void astore(Frame frame, int index) {
        Object val = frame.getOperandStack().popRef();
        frame.getLocalVars().setRef(index, val);
    }
}
