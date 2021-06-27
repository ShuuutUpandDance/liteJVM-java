package com.litejvm.instructions.base;

import com.litejvm.rtdata.Frame;

public interface Instruction {
    void fetchOperands(BytecodeReader reader);
    void execute(Frame frame);
}
