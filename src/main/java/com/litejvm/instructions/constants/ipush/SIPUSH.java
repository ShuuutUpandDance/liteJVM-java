package com.litejvm.instructions.constants.ipush;

import com.litejvm.instructions.base.BytecodeReader;

public class SIPUSH extends IPUSHInstruction {

    @Override
    public void fetchOperands(BytecodeReader reader) {
        val = reader.readInt16();
    }
}
