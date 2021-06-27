package com.litejvm.instructions.base;

public abstract class Index8Instruction implements Instruction{
    public int index;

    @Override
    public void fetchOperands(BytecodeReader reader) {
        index = reader.readUint8();
    }

    @Override
    public String toString() {
        return "Index8Instruction{" +
                "index=" + index +
                '}';
    }
}
