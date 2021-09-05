package com.litejvm.instructions.constants.ldc;

import com.litejvm.instructions.base.Index16Instruction;
import com.litejvm.rtdata.Frame;
import com.litejvm.rtdata.OperandStack;
import com.litejvm.rtdata.heap.ConstantPool;

public class LDC2_W extends Index16Instruction {
    @Override
    public void execute(Frame frame) {
        OperandStack operandStack = frame.operandStack;
        ConstantPool constantPool = frame.method.clazz.constantPool;
        Object constant = constantPool.getConstant(index);

        if (constant instanceof Long) {
            operandStack.pushLong((Long) constant);
        } else if (constant instanceof Double) {
            operandStack.pushDouble((Double) constant);
        } else {
            throw new ClassFormatError();
        }
    }
}
