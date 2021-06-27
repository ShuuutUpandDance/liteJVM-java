package com.litejvm.instructions.base;

public abstract class NoOperandsInstruction implements Instruction{
    @Override
    public void fetchOperands(BytecodeReader reader) {
        // do nothing
    }
}
