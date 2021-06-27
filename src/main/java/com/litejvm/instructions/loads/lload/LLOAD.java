package com.litejvm.instructions.loads.lload;

import com.litejvm.instructions.base.Index8Instruction;
import com.litejvm.rtdata.Frame;

public class LLOAD extends Index8Instruction implements LLOADInstruction {
    @Override
    public void execute(Frame frame) {
        lload(frame, index);
    }
}
