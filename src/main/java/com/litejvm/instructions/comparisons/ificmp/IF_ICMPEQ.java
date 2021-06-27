package com.litejvm.instructions.comparisons.ificmp;

import com.litejvm.instructions.base.BranchInstruction;
import com.litejvm.rtdata.Frame;
import com.litejvm.rtdata.OperandStack;

public class IF_ICMPEQ extends BranchInstruction {
    @Override
    public void execute(Frame frame) {
        OperandStack operandStack = frame.getOperandStack();
        int val2 = operandStack.popInt();
        int val1 = operandStack.popInt();
        if (val1 == val2) {
            branch(frame);
        }
    }
}
