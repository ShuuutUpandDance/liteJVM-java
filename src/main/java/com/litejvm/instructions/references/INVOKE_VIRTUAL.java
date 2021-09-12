package com.litejvm.instructions.references;

import com.litejvm.instructions.base.Index16Instruction;
import com.litejvm.instructions.base.MethodInvokeLogic;
import com.litejvm.rtdata.Frame;
import com.litejvm.rtdata.Object;
import com.litejvm.rtdata.OperandStack;
import com.litejvm.rtdata.heap.Class;
import com.litejvm.rtdata.heap.ConstantPool;
import com.litejvm.rtdata.heap.Method;
import com.litejvm.rtdata.heap.ref.MethodRef;
import com.litejvm.rtdata.heap.ref.MethodRefUtils;

public class INVOKE_VIRTUAL extends Index16Instruction {
    @Override
    public void execute(Frame frame) {
        Class currentClass = frame.method.clazz;
        ConstantPool constantPool = currentClass.constantPool;
        MethodRef methodRef = (MethodRef) constantPool.getConstant(index);
        Method resolvedMethod = methodRef.resolvedMethod();

        if (resolvedMethod.isStatic()){
            throw new IncompatibleClassChangeError();
        }

        Object thisRef = frame.operandStack.getRefFromTop(resolvedMethod.argSlotCount - 1);
        if (thisRef == null) {
            if ("println".equals(methodRef.name)) {
                println(frame, methodRef);
                return;
            }

            throw new NullPointerException();
        }

        if (resolvedMethod.isProtected() &&
                resolvedMethod.clazz.isSuperClassOf(currentClass) &&
                !resolvedMethod.clazz.getPackageName().equals(currentClass.getPackageName()) &&
                thisRef.clazz != currentClass &&
                !thisRef.clazz.isSubClassOf(currentClass)) {
            throw new IllegalAccessError();
        }

        Method methodToBeInvoked = MethodRefUtils.lookupMethodInClass(thisRef.clazz, methodRef.name, methodRef.descriptor);
        if (methodToBeInvoked == null || methodToBeInvoked.isAbstract()) {
            throw new AbstractMethodError();
        }

        MethodInvokeLogic.invokeMethod(frame, methodToBeInvoked);
    }

    private void println(Frame frame, MethodRef methodRef) {
        OperandStack operandStack = frame.operandStack;
        switch (methodRef.descriptor) {
            case "(Z)V":
                System.out.printf("%s\n", operandStack.popInt() != 0);
                break;
            case "(C)V":
            case "(I)V":
            case "(B)V":
            case "(S)V":
                System.out.printf("%s\n", operandStack.popInt());
                break;
            case "(F)V":
                System.out.printf("%s\n", operandStack.popFloat());
                break;
            case "(J)V":
                System.out.printf("%s\n", operandStack.popLong());
                break;
            case "(D)V":
                System.out.printf("%s\n", operandStack.popDouble());
                break;
            default:
                throw new RuntimeException("println: " + methodRef.method.descriptor);
        }
        operandStack.popRef();
    }
}
