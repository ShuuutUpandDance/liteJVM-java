package com.litejvm.instructions.stores.astore;

import com.litejvm.instructions.base.NoOperandsInstruction;
import com.litejvm.rtdata.Frame;

public class ASTORE_1 extends NoOperandsInstruction implements ASTOREInstruction {
    @Override
    public void execute(Frame frame) {
        astore(frame, 1);
    }
}
