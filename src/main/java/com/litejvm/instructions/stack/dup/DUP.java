package com.litejvm.instructions.stack.dup;

import com.litejvm.instructions.base.NoOperandsInstruction;
import com.litejvm.rtdata.Frame;
import com.litejvm.rtdata.OperandStack;
import com.litejvm.rtdata.Slot;

public class DUP extends NoOperandsInstruction {
    /*
    bottom -> top
    [...][c][b][a]
                 \_
                   |
                   V
    [...][c][b][a][a]
    */
    @Override
    public void execute(Frame frame) {
        OperandStack operandStack = frame.getOperandStack();
        Slot slot = operandStack.popSlot();
        operandStack.pushSlot(slot);
        operandStack.pushSlot(slot);
    }
}
