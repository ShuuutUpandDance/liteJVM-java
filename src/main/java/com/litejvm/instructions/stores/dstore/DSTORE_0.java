package com.litejvm.instructions.stores.dstore;

import com.litejvm.instructions.base.NoOperandsInstruction;
import com.litejvm.rtdata.Frame;

public class DSTORE_0 extends NoOperandsInstruction implements DSTOREInstruction {
    @Override
    public void execute(Frame frame) {
        dstore(frame, 0);
    }
}
