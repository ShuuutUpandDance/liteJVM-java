package com.litejvm.instructions.math;

import com.litejvm.instructions.base.BytecodeReader;
import com.litejvm.instructions.base.Instruction;
import com.litejvm.rtdata.Frame;
import com.litejvm.rtdata.LocalVars;

public class IINC implements Instruction {
    public int index;
    public int constant;

    @Override
    public void fetchOperands(BytecodeReader reader) {
        index = reader.readUint8();
        constant = reader.readInt8();
    }

    @Override
    public void execute(Frame frame) {
        LocalVars localVars = frame.getLocalVars();
        int val = localVars.getInt(index);
        val += constant;
        localVars.setInt(index, val);
    }

    @Override
    public String toString() {
        return "IINC{" +
                "index=" + index +
                ", constant=" + constant +
                '}';
    }
}
