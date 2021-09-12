package com.litejvm.rtdata;

public class Thread {
    public int pc;
    final Stack stack;

    public Thread() {
        this.stack = new Stack(1024);
    }

    public int getPc() {
        return pc;
    }

    public void setPc(int pc) {
        this.pc = pc;
    }

    public void pushFrame(Frame frame) {
        this.stack.push(frame);
    }

    public Frame popFrame() {
        return this.stack.pop();
    }

    public Frame topFrame() {
        return this.stack.top();
    }

    public Frame currentFrame() {
        return this.stack.top();
    }

    public boolean isStackEmpty() {
        return stack.isEmpty();
    }
}
