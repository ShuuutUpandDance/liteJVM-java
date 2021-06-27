package com.litejvm.instructions.comparisons.fcmp;

import com.litejvm.instructions.base.NoOperandsInstruction;
import com.litejvm.rtdata.Frame;
import com.litejvm.rtdata.OperandStack;

public class FCMPG extends NoOperandsInstruction implements FCMPInstruction{
    @Override
    public void execute(Frame frame) {
        OperandStack operandStack = frame.getOperandStack();
        float v2 = operandStack.popFloat();
        float v1 = operandStack.popFloat();

        if (containsNaN(v1, v2)) {
            operandStack.pushInt(1);
        } else {
            operandStack.pushInt(Float.compare(v1, v2));
        }
    }
}
