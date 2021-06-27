package com.litejvm.instructions.extended;

import com.litejvm.instructions.base.BranchInstruction;
import com.litejvm.instructions.base.BytecodeReader;
import com.litejvm.rtdata.Frame;

public class GOTO_W extends BranchInstruction {

    @Override
    public void fetchOperands(BytecodeReader reader) {
        offset = reader.readInt32();
    }

    @Override
    public void execute(Frame frame) {
        branch(frame, offset);
    }
}
