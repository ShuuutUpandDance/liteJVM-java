package com.litejvm.instructions.loads.iload;

import com.litejvm.instructions.base.NoOperandsInstruction;
import com.litejvm.rtdata.Frame;

public class ILOAD_2 extends NoOperandsInstruction implements ILOADInstruction{
    @Override
    public void execute(Frame frame) {
        iload(frame, 2);
    }
}
