package com.litejvm.instructions.stores.fstore;

import com.litejvm.instructions.base.NoOperandsInstruction;
import com.litejvm.rtdata.Frame;

public class FSTORE_1 extends NoOperandsInstruction implements FSTOREInstruction{
    @Override
    public void execute(Frame frame) {
        fstore(frame, 1);
    }
}
