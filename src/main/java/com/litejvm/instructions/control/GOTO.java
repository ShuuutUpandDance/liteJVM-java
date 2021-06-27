package com.litejvm.instructions.control;

import com.litejvm.instructions.base.BranchInstruction;
import com.litejvm.rtdata.Frame;

public class GOTO extends BranchInstruction {
    @Override
    public void execute(Frame frame) {
        branch(frame);
    }
}
