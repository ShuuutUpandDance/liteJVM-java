package com.litejvm.instructions.references;

import com.litejvm.instructions.base.BytecodeReader;
import com.litejvm.instructions.base.Instruction;
import com.litejvm.instructions.base.MethodInvokeLogic;
import com.litejvm.rtdata.Frame;
import com.litejvm.rtdata.Object;
import com.litejvm.rtdata.heap.ConstantPool;
import com.litejvm.rtdata.heap.Method;
import com.litejvm.rtdata.heap.ref.InterfaceMethodRef;
import com.litejvm.rtdata.heap.ref.MethodRefUtils;

public class INVOKE_INTERFACE implements Instruction {
    int index;

    int count; // unused
    int zero; // unused

    @Override
    public void fetchOperands(BytecodeReader reader) {
        index = reader.readUint16();

        count = reader.readUint8();
        zero = reader.readUint8();
    }

    @Override
    public void execute(Frame frame) {
        ConstantPool constantPool = frame.method.clazz.constantPool;
        InterfaceMethodRef methodRef = (InterfaceMethodRef) constantPool.getConstant(index);
        Method resolvedMethod = methodRef.resolvedInterfaceMethod();

        if (resolvedMethod.isStatic() || resolvedMethod.isPrivate()){
            throw new IncompatibleClassChangeError();
        }

        Object thisRef = frame.operandStack.getRefFromTop(resolvedMethod.argSlotCount - 1);
        if (thisRef == null) {
            throw new NullPointerException();
        }

        if (!thisRef.clazz.isImplements(methodRef.resolvedClass())) {
            throw new IncompatibleClassChangeError();
        }

        Method methodToBeInvoked = MethodRefUtils.lookupMethodInClass(thisRef.clazz, methodRef.name, methodRef.descriptor);
        if (methodToBeInvoked == null || methodToBeInvoked.isAbstract()) {
            throw new AbstractMethodError();
        }

        if (!methodToBeInvoked.isPublic()) {
            throw new IllegalAccessError();
        }

        MethodInvokeLogic.invokeMethod(frame, methodToBeInvoked);
    }
}
