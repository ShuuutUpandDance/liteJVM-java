package com.litejvm.instructions.references;

import com.litejvm.instructions.base.Index16Instruction;
import com.litejvm.rtdata.Frame;
import com.litejvm.rtdata.OperandStack;
import com.litejvm.rtdata.heap.ConstantPool;
import com.litejvm.rtdata.heap.ref.MethodRef;

public class INVOKE_VIRTUAL extends Index16Instruction {
    @Override
    public void execute(Frame frame) {
        ConstantPool constantPool = frame.method.clazz.constantPool;
        MethodRef methodRef = (MethodRef) constantPool.getConstant(index);
        if ("println".equals(methodRef.name)) {
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
}
