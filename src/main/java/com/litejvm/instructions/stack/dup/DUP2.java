package com.litejvm.instructions.stack.dup;

import com.litejvm.instructions.base.NoOperandsInstruction;
import com.litejvm.rtdata.Frame;
import com.litejvm.rtdata.OperandStack;
import com.litejvm.rtdata.Slot;

public class DUP2 extends NoOperandsInstruction {

    /*
    bottom -> top
    [...][c][b][a]____
              \____   |
                   |  |
                   V  V
    [...][c][b][a][b][a]
    */
    @Override
    public void execute(Frame frame) {
        OperandStack operandStack = frame.getOperandStack();
        Slot slot1 = operandStack.popSlot();
        Slot slot2 = operandStack.popSlot();
        operandStack.pushSlot(Slot.copyFrom(slot2));
        operandStack.pushSlot(Slot.copyFrom(slot1));
        operandStack.pushSlot(Slot.copyFrom(slot2));
        operandStack.pushSlot(Slot.copyFrom(slot1));
    }
}
