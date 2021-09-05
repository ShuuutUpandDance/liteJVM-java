package com.litejvm.instructions.references;

import com.litejvm.instructions.base.Index16Instruction;
import com.litejvm.rtdata.Frame;
import com.litejvm.rtdata.Object;
import com.litejvm.rtdata.OperandStack;
import com.litejvm.rtdata.heap.*;
import com.litejvm.rtdata.heap.Class;
import com.litejvm.rtdata.heap.ref.FieldRef;

import java.lang.reflect.MalformedParametersException;

/**
 * To assign value to an instance field
 */
public class PUT_FIELD extends Index16Instruction {
    @Override
    public void execute(Frame frame) {
        Method curMethod = frame.method;
        Class curClass = curMethod.clazz;
        ConstantPool constantPool = curClass.constantPool;

        FieldRef fieldRef = (FieldRef) constantPool.getConstant(this.index);
        Field field = fieldRef.resolvedField();

        if (field.isStatic()) {
            throw new IncompatibleClassChangeError();
        }

        if (field.isFinal()) {
            if (curClass != field.clazz || !"<init>".equals(curMethod.name)) {
                // final field should only be assigned within init method
                throw new IllegalAccessError();
            }
        }

        String descriptor = field.descriptor;
        int slotId = field.slotId;
        OperandStack operandStack = frame.operandStack;

        switch (descriptor.charAt(0)) {
            case 'Z':
            case 'B':
            case 'C':
            case 'S':
            case 'I': {
                int val = operandStack.popInt();
                Object ref = operandStack.popRef();
                if (ref == null) {
                    throw new NullPointerException();
                }
                ref.fields.setInt(slotId, val);
                break;
            }
            case 'F': {
                float val = operandStack.popFloat();
                Object ref = operandStack.popRef();
                if (ref == null) {
                    throw new NullPointerException();
                }
                ref.fields.setFloat(slotId, val);
                break;
            }
            case 'J': {
                long val = operandStack.popLong();
                Object ref = operandStack.popRef();
                if (ref == null) {
                    throw new NullPointerException();
                }
                ref.fields.setLong(slotId, val);
                break;
            }
            case 'D': {
                double val = operandStack.popDouble();
                Object ref = operandStack.popRef();
                if (ref == null) {
                    throw new NullPointerException();
                }
                ref.fields.setDouble(slotId, val);
                break;
            }
            case 'L':
            case '[': {
                Object val = operandStack.popRef();
                Object ref = operandStack.popRef();
                if (ref == null) {
                    throw new NullPointerException();
                }
                ref.fields.setRef(slotId, val);
                break;
            }
            default:
                throw new MalformedParametersException(String.format("Unsupported descriptor: %s", descriptor));
        }
    }
}
