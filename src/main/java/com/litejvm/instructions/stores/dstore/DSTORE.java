package com.litejvm.instructions.stores.dstore;

import com.litejvm.instructions.base.Index8Instruction;
import com.litejvm.rtdata.Frame;

public class DSTORE extends Index8Instruction implements DSTOREInstruction {
    @Override
    public void execute(Frame frame) {
        dstore(frame, index);
    }
}
