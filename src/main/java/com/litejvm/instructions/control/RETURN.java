package com.litejvm.instructions.control;

import com.litejvm.instructions.base.NoOperandsInstruction;
import com.litejvm.rtdata.Frame;

public class RETURN extends NoOperandsInstruction {
    @Override
    public void execute(Frame frame) {
        frame.thread.popFrame();
    }
}
