package com.litejvm.instructions.control;

import com.litejvm.instructions.base.NoOperandsInstruction;
import com.litejvm.rtdata.Frame;
import com.litejvm.rtdata.Object;

public class ARETURN extends NoOperandsInstruction {
    @Override
    public void execute(Frame frame) {
        Frame currentFrame = frame.thread.popFrame();
        Frame invokerFrame = frame.thread.topFrame();
        Object retVal = currentFrame.operandStack.popRef();
        invokerFrame.operandStack.pushRef(retVal);
    }
}
