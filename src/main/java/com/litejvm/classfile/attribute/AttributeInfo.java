package com.litejvm.classfile.attribute;

import com.litejvm.classfile.ClassReader;

public interface AttributeInfo {
    void readInfo(ClassReader reader);
}
