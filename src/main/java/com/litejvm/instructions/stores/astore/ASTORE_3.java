package com.litejvm.instructions.stores.astore;

import com.litejvm.instructions.base.NoOperandsInstruction;
import com.litejvm.rtdata.Frame;

public class ASTORE_3 extends NoOperandsInstruction implements ASTOREInstruction {
    @Override
    public void execute(Frame frame) {
        astore(frame, 3);
    }
}
