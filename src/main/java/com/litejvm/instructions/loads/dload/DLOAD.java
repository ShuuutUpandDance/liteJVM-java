package com.litejvm.instructions.loads.dload;

import com.litejvm.instructions.base.Index8Instruction;
import com.litejvm.rtdata.Frame;

public class DLOAD extends Index8Instruction implements DLOADInstruction {
    @Override
    public void execute(Frame frame) {
        dload(frame, index);
    }
}
