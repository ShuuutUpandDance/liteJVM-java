package com.litejvm.instructions.stores.lstore;

import com.litejvm.instructions.base.NoOperandsInstruction;
import com.litejvm.rtdata.Frame;

public class LSTORE_1 extends NoOperandsInstruction implements LSTOREInstruction {
    @Override
    public void execute(Frame frame) {
        lstore(frame, 1);
    }
}
