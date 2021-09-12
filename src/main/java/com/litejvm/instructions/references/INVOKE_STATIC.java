package com.litejvm.instructions.references;

import com.litejvm.instructions.base.ClassInitLogic;
import com.litejvm.instructions.base.Index16Instruction;
import com.litejvm.instructions.base.MethodInvokeLogic;
import com.litejvm.rtdata.Frame;
import com.litejvm.rtdata.heap.Class;
import com.litejvm.rtdata.heap.ConstantPool;
import com.litejvm.rtdata.heap.Method;
import com.litejvm.rtdata.heap.ref.MethodRef;

public class INVOKE_STATIC extends Index16Instruction {
    @Override
    public void execute(Frame frame) {
        ConstantPool constantPool = frame.method.clazz.constantPool;
        MethodRef methodRef = (MethodRef) constantPool.getConstant(index);
        Method resolvedMethod = methodRef.resolvedMethod();
        Class clazz = resolvedMethod.clazz;
        if (!clazz.isInitStarted()) {
            frame.revertNextPC();
            ClassInitLogic.initClass(frame.thread, clazz);
            return;
        }

        if (!resolvedMethod.isStatic()) {
            throw new IncompatibleClassChangeError();
        }
        MethodInvokeLogic.invokeMethod(frame, resolvedMethod);
    }
}
