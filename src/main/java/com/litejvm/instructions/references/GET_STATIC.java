package com.litejvm.instructions.references;

import com.litejvm.instructions.base.Index16Instruction;
import com.litejvm.rtdata.Frame;
import com.litejvm.rtdata.OperandStack;
import com.litejvm.rtdata.heap.Class;
import com.litejvm.rtdata.heap.ConstantPool;
import com.litejvm.rtdata.heap.Field;
import com.litejvm.rtdata.heap.Slots;
import com.litejvm.rtdata.heap.ref.FieldRef;

import java.lang.reflect.MalformedParametersException;

/**
 * To get value from a static field
 */
public class GET_STATIC extends Index16Instruction {
    @Override
    public void execute(Frame frame) {
        ConstantPool constantPool = frame.method.clazz.constantPool;
        FieldRef fieldRef = (FieldRef) constantPool.getConstant(this.index);
        Field field = fieldRef.resolvedField();
        Class fieldClass = field.clazz;

        if (!field.isStatic()) {
            throw new IncompatibleClassChangeError();
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
