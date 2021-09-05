package com.litejvm.instructions.references;

import com.litejvm.instructions.base.Index16Instruction;
import com.litejvm.rtdata.Frame;
import com.litejvm.rtdata.Object;
import com.litejvm.rtdata.OperandStack;
import com.litejvm.rtdata.heap.Class;
import com.litejvm.rtdata.heap.ConstantPool;
import com.litejvm.rtdata.heap.ref.ClassRef;

/**
 * Determine if object is of given type
 */
public class INSTANCE_OF extends Index16Instruction {
    @Override
    public void execute(Frame frame) {
        OperandStack operandStack = frame.operandStack;
        Object ref = operandStack.popRef();
        if (ref == null) {
            operandStack.pushInt(0);
            return;
        }

        ConstantPool constantPool = frame.method.clazz.constantPool;
        ClassRef classRef = (ClassRef) constantPool.getConstant(index);
        Class clazz = classRef.resolvedClass();
        if (ref.isInstanceOf(clazz)) {
            operandStack.pushInt(1);
        } else {
            operandStack.pushInt(0);
        }
    }
}
