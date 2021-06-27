package com.litejvm.rtdata;

public class Frame {
    Frame lower;
    final LocalVars localVars;
    final OperandStack operandStack;
    Thread thread;
    int nextPC;

    public Frame(int maxLocals, int maxStack, Thread thread) {
        this.localVars = new LocalVars(maxLocals);
        this.operandStack = new OperandStack(maxStack);
        this.thread = thread;
    }

    public Thread getThread() {
        return thread;
    }

    public void setNextPC(int nextPC) {
        this.nextPC = nextPC;
    }

    public int getNextPC() {
        return nextPC;
    }

    public LocalVars getLocalVars() {
        return localVars;
    }

    public OperandStack getOperandStack() {
        return operandStack;
    }
}
