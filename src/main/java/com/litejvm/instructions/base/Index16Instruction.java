package com.litejvm.instructions.base;

public abstract class Index16Instruction implements Instruction{
    protected int index;

    @Override
    public void fetchOperands(BytecodeReader reader) {
        index = reader.readUint16();
    }
}
