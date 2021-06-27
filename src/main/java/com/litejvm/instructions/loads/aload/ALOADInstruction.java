package com.litejvm.instructions.loads.aload;

import com.litejvm.rtdata.Frame;
import com.litejvm.rtdata.Object;

public interface ALOADInstruction {
    default void aload(Frame frame, int index) {
        Object val = frame.getLocalVars().getRef(index);
        frame.getOperandStack().pushRef(val);
    }
}
