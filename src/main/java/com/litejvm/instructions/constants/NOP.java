package com.litejvm.instructions.constants;

import com.litejvm.instructions.base.NoOperandsInstruction;
import com.litejvm.rtdata.Frame;

public class NOP extends NoOperandsInstruction {

    @Override
    public void execute(Frame frame) {
        // do nothing
    }
}
