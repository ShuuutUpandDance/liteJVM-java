package com.litejvm.instructions.extended;

import com.litejvm.instructions.base.BranchInstruction;
import com.litejvm.rtdata.Frame;
import com.litejvm.rtdata.Object;

public class IFNONNULL extends BranchInstruction {
    @Override
    public void execute(Frame frame) {
        Object ref = frame.getOperandStack().popRef();
        if (ref != null) {
            branch(frame, offset);
        }
    }
}
