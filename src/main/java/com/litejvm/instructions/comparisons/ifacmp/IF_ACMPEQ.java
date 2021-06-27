package com.litejvm.instructions.comparisons.ifacmp;

import com.litejvm.instructions.base.BranchInstruction;
import com.litejvm.rtdata.Frame;
import com.litejvm.rtdata.Object;
import com.litejvm.rtdata.OperandStack;

public class IF_ACMPEQ extends BranchInstruction {
    @Override
    public void execute(Frame frame) {
        OperandStack operandStack = frame.getOperandStack();
        Object ref2 = operandStack.popRef();
        Object ref1 = operandStack.popRef();
        if (ref1 == ref2) {
            branch(frame);
        }
    }
}
