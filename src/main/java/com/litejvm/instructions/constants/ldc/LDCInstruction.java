package com.litejvm.instructions.constants.ldc;

import com.litejvm.rtdata.Frame;
import com.litejvm.rtdata.OperandStack;
import com.litejvm.rtdata.heap.ConstantPool;

public interface LDCInstruction {
    default void ldc(Frame frame, int index) {
        OperandStack operandStack = frame.operandStack;
        ConstantPool constantPool = frame.method.clazz.constantPool;
        Object constant = constantPool.getConstant(index);

        if (constant instanceof Integer) {
            operandStack.pushInt((Integer) constant);
        } else if (constant instanceof Float) {
            operandStack.pushFloat((Float) constant);
        } else {
            throw new RuntimeException("TODO: ldc");
        }
    }
}
