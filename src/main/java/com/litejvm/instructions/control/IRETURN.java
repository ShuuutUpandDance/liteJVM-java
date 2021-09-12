package com.litejvm.instructions.control;

import com.litejvm.instructions.base.NoOperandsInstruction;
import com.litejvm.rtdata.Frame;

public class IRETURN extends NoOperandsInstruction {
    @Override
    public void execute(Frame frame) {
        Frame currentFrame = frame.thread.popFrame();
        Frame invokerFrame = frame.thread.topFrame();
        int retVal = currentFrame.operandStack.popInt();
        invokerFrame.operandStack.pushInt(retVal);
    }
}
