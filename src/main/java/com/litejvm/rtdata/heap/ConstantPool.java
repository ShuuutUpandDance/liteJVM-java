package com.litejvm.rtdata.heap;

public class ConstantPool {
    public Class clazz;
    public Object[] consts;

    public Object getConstant(int index) {
        Object constant = this.consts[index];
        if (constant != null) {
            return constant;
        } else {
            throw new RuntimeException(String.format("No constants at index %d", index));
        }
    }
}
