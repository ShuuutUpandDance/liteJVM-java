package com.litejvm.instructions.comparisons.ifcond;

import com.litejvm.instructions.base.BranchInstruction;
import com.litejvm.rtdata.Frame;

public class IFGE extends BranchInstruction {
    @Override
    public void execute(Frame frame) {
        int val = frame.getOperandStack().popInt();
        if (val >= 0) {
            branch(frame);
        }
    }
}
