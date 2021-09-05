package com.litejvm.instructions.references;

import com.litejvm.instructions.base.Index16Instruction;
import com.litejvm.rtdata.Frame;
import com.litejvm.rtdata.Object;
import com.litejvm.rtdata.OperandStack;
import com.litejvm.rtdata.heap.ConstantPool;
import com.litejvm.rtdata.heap.Field;
import com.litejvm.rtdata.heap.Slots;
import com.litejvm.rtdata.heap.ref.FieldRef;

import java.lang.reflect.MalformedParametersException;

/**
 * To get value from an instance field
 */
public class GET_FIELD extends Index16Instruction {
    @Override
    public void execute(Frame frame) {
        ConstantPool constantPool = frame.method.clazz.constantPool;
        FieldRef fieldRef = (FieldRef) constantPool.getConstant(this.index);
        Field field = fieldRef.resolvedField();

        if (field.isStatic()) {
            throw new IncompatibleClassChangeError();
        }

        OperandStack operandStack = frame.operandStack;
        Object ref = operandStack.popRef();
        if (ref == null) {
            throw new NullPointerException();
        }

        String descriptor = field.descriptor;
        int slotId = field.slotId;
        Slots slots = ref.fields;

        switch (descriptor.charAt(0)) {
            case 'Z':
            case 'B':
            case 'C':
            case 'S':
            case 'I': {
                operandStack.pushInt(slots.getInt(slotId));
                break;
            }
            case 'F': {
                operandStack.pushFloat(slots.getFloat(slotId));
                break;
            }
            case 'J': {
                operandStack.pushLong(slots.getLong(slotId));
                break;
            }
            case 'D': {
                operandStack.pushDouble(slots.getDouble(slotId));
                break;
            }
            case 'L':
            case '[': {
                operandStack.pushRef(slots.getRef(slotId));
                break;
            }
            default:
                throw new MalformedParametersException(String.format("Unsupported descriptor: %s", descriptor));
        }
    }
}
