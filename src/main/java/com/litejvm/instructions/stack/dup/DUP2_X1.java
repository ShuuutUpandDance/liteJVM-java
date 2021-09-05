package com.litejvm.instructions.stack.dup;

import com.litejvm.instructions.base.NoOperandsInstruction;
import com.litejvm.rtdata.Frame;
import com.litejvm.rtdata.OperandStack;
import com.litejvm.rtdata.Slot;

public class DUP2_X1 extends NoOperandsInstruction {

    /*
    bottom -> top
    [...][c][b][a]
           _/ __/
          |  |
          V  V
    [...][b][a][c][b][a]
    */
    @Override
    public void execute(Frame frame) {
        OperandStack operandStack = frame.getOperandStack();
        Slot slot1 = operandStack.popSlot();
        Slot slot2 = operandStack.popSlot();
        Slot slot3 = operandStack.popSlot();
        operandStack.pushSlot(Slot.copyFrom(slot2));
        operandStack.pushSlot(Slot.copyFrom(slot1));
        operandStack.pushSlot(Slot.copyFrom(slot3));
        operandStack.pushSlot(Slot.copyFrom(slot2));
        operandStack.pushSlot(Slot.copyFrom(slot1));
    }
}
