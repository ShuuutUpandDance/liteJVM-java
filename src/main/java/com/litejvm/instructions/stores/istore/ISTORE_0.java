package com.litejvm.instructions.stores.istore;

import com.litejvm.instructions.base.NoOperandsInstruction;
import com.litejvm.rtdata.Frame;

public class ISTORE_0 extends NoOperandsInstruction implements ISTOREInstruction {
    @Override
    public void execute(Frame frame) {
        istore(frame, 0);
    }
}
