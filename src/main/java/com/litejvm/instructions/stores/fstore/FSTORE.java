package com.litejvm.instructions.stores.fstore;

import com.litejvm.instructions.base.Index8Instruction;
import com.litejvm.rtdata.Frame;

public class FSTORE extends Index8Instruction implements FSTOREInstruction {
    @Override
    public void execute(Frame frame) {
        fstore(frame, index);
    }
}
