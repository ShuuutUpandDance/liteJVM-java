package com.litejvm.rtdata;

public class Frame {
    Frame lower;
    final LocalVars localVars;
    final OperandStack operandStack;

    public Frame(int maxLocals, int maxStack) {
        this.localVars = new LocalVars(maxLocals);
        this.operandStack = new OperandStack(maxStack);
    }
}
