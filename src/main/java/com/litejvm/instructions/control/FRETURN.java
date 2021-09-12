package com.litejvm.instructions.control;

import com.litejvm.instructions.base.NoOperandsInstruction;
import com.litejvm.rtdata.Frame;

public class FRETURN extends NoOperandsInstruction {
    @Override
    public void execute(Frame frame) {
        Frame currentFrame = frame.thread.popFrame();
        Frame invokerFrame = frame.thread.topFrame();
        float retVal = currentFrame.operandStack.popFloat();
        invokerFrame.operandStack.pushFloat(retVal);
    }
}
