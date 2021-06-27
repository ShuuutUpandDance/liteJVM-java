package com.litejvm.instructions.stores.astore;

import com.litejvm.instructions.base.Index8Instruction;
import com.litejvm.rtdata.Frame;

public class ASTORE extends Index8Instruction implements ASTOREInstruction{
    @Override
    public void execute(Frame frame) {
        astore(frame, index);
    }
}
