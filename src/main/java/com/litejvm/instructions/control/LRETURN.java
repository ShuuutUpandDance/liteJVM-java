package com.litejvm.instructions.control;

import com.litejvm.instructions.base.NoOperandsInstruction;
import com.litejvm.rtdata.Frame;

public class LRETURN extends NoOperandsInstruction {
    @Override
    public void execute(Frame frame) {
        Frame currentFrame = frame.thread.popFrame();
        Frame invokerFrame = frame.thread.topFrame();
        long retVal = currentFrame.operandStack.popLong();
        invokerFrame.operandStack.pushLong(retVal);
    }
}
