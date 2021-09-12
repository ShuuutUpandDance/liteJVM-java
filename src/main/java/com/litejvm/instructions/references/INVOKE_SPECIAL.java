package com.litejvm.instructions.references;

import com.litejvm.instructions.base.Index16Instruction;
import com.litejvm.instructions.base.MethodInvokeLogic;
import com.litejvm.rtdata.Frame;
import com.litejvm.rtdata.Object;
import com.litejvm.rtdata.heap.Class;
import com.litejvm.rtdata.heap.ConstantPool;
import com.litejvm.rtdata.heap.Method;
import com.litejvm.rtdata.heap.ref.MethodRef;
import com.litejvm.rtdata.heap.ref.MethodRefUtils;

public class INVOKE_SPECIAL extends Index16Instruction {
    @Override
    public void execute(Frame frame) {
        Class currentClass = frame.method.clazz;
        ConstantPool constantPool = currentClass.constantPool;
        MethodRef methodRef = (MethodRef) constantPool.getConstant(index);
        Class resolvedClass = methodRef.resolvedClass();
        Method resolvedMethod = methodRef.resolvedMethod();

        if (resolvedMethod.name.equals("<init>") && resolvedMethod.clazz != resolvedClass) {
            throw new NoSuchMethodError();
        }

        if (resolvedMethod.isStatic()){
            throw new IncompatibleClassChangeError();
        }

        Object thisRef = frame.operandStack.getRefFromTop(resolvedMethod.argSlotCount - 1);
        if (thisRef == null) {
            throw new NullPointerException();
        }

        if (resolvedMethod.isProtected() &&
                resolvedMethod.clazz.isSuperClassOf(currentClass) &&
                !resolvedMethod.clazz.getPackageName().equals(currentClass.getPackageName()) &&
                thisRef.clazz != currentClass &&
                !thisRef.clazz.isSubClassOf(currentClass)) {
            throw new IllegalAccessError();
        }

        Method methodToBeInvoked = resolvedMethod;
        if (currentClass.isSuper() &&
                resolvedClass.isSuperClassOf(currentClass) &&
                !resolvedMethod.name.equals("<init>")) {
            methodToBeInvoked = MethodRefUtils.lookupMethodInClass(currentClass.superClass, methodRef.name, methodRef.descriptor);
        }

        if (methodToBeInvoked == null || methodToBeInvoked.isAbstract()) {
            throw new AbstractMethodError();
        }

        MethodInvokeLogic.invokeMethod(frame, methodToBeInvoked);
    }
}
