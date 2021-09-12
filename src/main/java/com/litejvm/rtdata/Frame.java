package com.litejvm.rtdata;

import com.litejvm.rtdata.heap.Method;

public class Frame {
    public Frame lower;
    public final LocalVars localVars;
    public final OperandStack operandStack;
    public Thread thread;
    public Method method;
    public int nextPC;

    public Frame(int maxLocals, int maxStack, Thread thread) {
        this.localVars = new LocalVars(maxLocals);
        this.operandStack = new OperandStack(maxStack);
        this.thread = thread;
    }

    public Frame(Thread thread, Method method) {
        this.thread = thread;
        this.method = method;
        this.localVars = new LocalVars(method.maxLocals);
        this.operandStack = new OperandStack(method.maxStack);
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

    public void revertNextPC() {
        this.nextPC = this.thread.pc;
    }
}
