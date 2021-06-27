package com.litejvm.instructions.constants.ipush;

import com.litejvm.instructions.base.BytecodeReader;

public class BIPUSH extends IPUSHInstruction {

    @Override
    public void fetchOperands(BytecodeReader reader) {
        val = reader.readInt8();
    }
}
