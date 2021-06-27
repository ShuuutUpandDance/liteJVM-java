package com.litejvm.instructions.loads.lload;

import com.litejvm.instructions.base.NoOperandsInstruction;
import com.litejvm.rtdata.Frame;

public class LLOAD_3 extends NoOperandsInstruction implements LLOADInstruction {
    @Override
    public void execute(Frame frame) {
        lload(frame, 3);
    }
}
