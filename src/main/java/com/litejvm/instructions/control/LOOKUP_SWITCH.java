package com.litejvm.instructions.control;

import com.litejvm.instructions.base.BranchInstruction;
import com.litejvm.instructions.base.BytecodeReader;
import com.litejvm.rtdata.Frame;

public class LOOKUP_SWITCH extends BranchInstruction {
    int defaultOffset;
    int npairs;
    int[] matchOffsets;

    @Override
    public void fetchOperands(BytecodeReader reader) {
        reader.skipPadding();
        defaultOffset = reader.readInt32();
        npairs = reader.readInt32();
        matchOffsets = reader.readInt32s(npairs * 2);
    }

    @Override
    public void execute(Frame frame) {
        int key = frame.getOperandStack().popInt();
        for (int i = 0; i < npairs * 2; i++) {
            if (matchOffsets[i] == key) {
                branch(frame, matchOffsets[i + 1]);
                return;
            }
        }
        branch(frame, defaultOffset);
    }
}
