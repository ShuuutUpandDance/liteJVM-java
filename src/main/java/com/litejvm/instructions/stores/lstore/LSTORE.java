package com.litejvm.instructions.stores.lstore;

import com.litejvm.instructions.base.Index8Instruction;
import com.litejvm.rtdata.Frame;

public class LSTORE extends Index8Instruction implements LSTOREInstruction {
    @Override
    public void execute(Frame frame) {
        lstore(frame, index);
    }
}
