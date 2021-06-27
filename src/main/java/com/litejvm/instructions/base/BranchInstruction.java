package com.litejvm.instructions.base;

import com.litejvm.rtdata.Frame;

public abstract class BranchInstruction implements Instruction{
    protected int offset;

    @Override
    public void fetchOperands(BytecodeReader reader) {
        offset = reader.readInt16();
    }

    protected void branch(Frame frame) {
        branch(frame, offset);
    }

    protected void branch(Frame frame, int offset) {
        int pc = frame.getThread().getPc();
        int nextPC = pc + offset;
        frame.setNextPC(nextPC);
    }

    @Override
    public String toString() {
        return "BranchInstruction{" +
                "offset=" + offset +
                '}';
    }
}
