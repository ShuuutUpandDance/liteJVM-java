package com.litejvm.instructions.comparisons.dcmp;

import com.litejvm.instructions.base.NoOperandsInstruction;
import com.litejvm.rtdata.Frame;
import com.litejvm.rtdata.OperandStack;

public class DCMPL extends NoOperandsInstruction implements DCMPInstruction {
    @Override
    public void execute(Frame frame) {
        OperandStack operandStack = frame.getOperandStack();
        double v2 = operandStack.popDouble();
        double v1 = operandStack.popDouble();

        if (containsNaN(v1, v2)) {
            operandStack.pushInt(-1);
        } else {
            operandStack.pushInt(Double.compare(v1, v2));
        }
    }
}
