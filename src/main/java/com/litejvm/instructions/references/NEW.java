package com.litejvm.instructions.references;

import com.litejvm.instructions.base.ClassInitLogic;
import com.litejvm.instructions.base.Index16Instruction;
import com.litejvm.rtdata.Frame;
import com.litejvm.rtdata.Object;
import com.litejvm.rtdata.heap.Class;
import com.litejvm.rtdata.heap.ConstantPool;
import com.litejvm.rtdata.heap.ref.ClassRef;

public class NEW extends Index16Instruction {
    @Override
    public void execute(Frame frame) {
        ConstantPool constantPool = frame.method.clazz.constantPool;
        ClassRef classRef = (ClassRef) constantPool.getConstant(index);
        Class aClass = classRef.resolvedClass();

        if (!aClass.isInitStarted()) {
            frame.revertNextPC();
            ClassInitLogic.initClass(frame.thread, aClass);
            return;
        }

        if (aClass.isInterface() || aClass.isAbstract()) {
            throw new RuntimeException(new InstantiationError());
        }
        Object ref = new Object(aClass);
        frame.operandStack.pushRef(ref);
    }
}
