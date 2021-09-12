package com.litejvm.instructions.control;

import com.litejvm.instructions.base.NoOperandsInstruction;
import com.litejvm.rtdata.Frame;

public class DRETURN extends NoOperandsInstruction {
    @Override
    public void execute(Frame frame) {
        Frame currentFrame = frame.thread.popFrame();
        Frame invokerFrame = frame.thread.topFrame();
        double retVal = currentFrame.operandStack.popDouble();
        invokerFrame.operandStack.pushDouble(retVal);
    }
}
