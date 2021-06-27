package com.litejvm.instructions.stores.istore;

import com.litejvm.instructions.base.Index8Instruction;
import com.litejvm.rtdata.Frame;

public class ISTORE extends Index8Instruction implements ISTOREInstruction {
    @Override
    public void execute(Frame frame) {
        istore(frame, index);
    }
}
