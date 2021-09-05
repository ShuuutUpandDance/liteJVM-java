package com.litejvm.instructions.references;

import com.litejvm.instructions.base.Index16Instruction;
import com.litejvm.rtdata.Frame;
import com.litejvm.rtdata.OperandStack;
import com.litejvm.rtdata.heap.*;
import com.litejvm.rtdata.heap.Class;
import com.litejvm.rtdata.heap.ref.FieldRef;

import java.lang.reflect.MalformedParametersException;

/**
 * To assign value to a static variable
 */
public class PUT_STATIC extends Index16Instruction {
    @Override
    public void execute(Frame frame) {
        Method curMethod = frame.method;
        Class curClass = curMethod.clazz;
        ConstantPool constantPool = curClass.constantPool;

        FieldRef fieldRef = (FieldRef) constantPool.getConstant(this.index);
        Field field = fieldRef.resolvedField();
        Class fieldClass = field.clazz;

        if (!field.isStatic()) {
            throw new IncompatibleClassChangeError();
        }

        if (field.isFinal()) {
            if (curClass != fieldClass || !"<clinit>".equals(curMethod.name)) {
                // static final field should only be assigned within <clinit> method
                throw new IllegalAccessError();
            }
        }

        String descriptor = field.descriptor;
        int slotId = field.slotId;
        Slots slots = fieldClass.staticVars;
        OperandStack operandStack = frame.operandStack;

        switch (descriptor.charAt(0)) {
            case 'Z':
            case 'B':
            case 'C':
            case 'S':
            case 'I': {
                slots.setInt(slotId, operandStack.popInt());
                break;
            }
            case 'F': {
                slots.setFloat(slotId, operandStack.popFloat());
                break;
            }
            case 'J': {
                slots.setLong(slotId, operandStack.popLong());
                break;
            }
            case 'D': {
                slots.setDouble(slotId, operandStack.popDouble());
                break;
            }
            case 'L':
            case '[': {
                slots.setRef(slotId, operandStack.popRef());
                break;
            }
            default:
                throw new MalformedParametersException(String.format("Unsupported descriptor: %s", descriptor));
        }
    }
}
