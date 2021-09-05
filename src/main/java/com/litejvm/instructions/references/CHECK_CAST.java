package com.litejvm.instructions.references;

import com.litejvm.instructions.base.Index16Instruction;
import com.litejvm.rtdata.Frame;
import com.litejvm.rtdata.Object;
import com.litejvm.rtdata.OperandStack;
import com.litejvm.rtdata.heap.Class;
import com.litejvm.rtdata.heap.ConstantPool;
import com.litejvm.rtdata.heap.ref.ClassRef;

/**
 * Check whether object can be cast to given type
 */
public class CHECK_CAST extends Index16Instruction {
    @Override
    public void execute(Frame frame) {
        OperandStack operandStack = frame.operandStack;
        Object ref = operandStack.popRef();
        operandStack.pushRef(ref);
        if (ref == null) {
            return;
        }

        ConstantPool constantPool = frame.method.clazz.constantPool;
        ClassRef classRef = (ClassRef) constantPool.getConstant(index);
        Class clazz = classRef.resolvedClass();
        if (!ref.isInstanceOf(clazz)) {
            throw new ClassCastException();
        }
    }
}
