package com.litejvm.rtdata;

import com.litejvm.rtdata.heap.Class;
import com.litejvm.rtdata.heap.Slots;

public class Object {
    public Class clazz;
    public Slots fields;

    public Object(Class clazz) {
        this.clazz = clazz;
        fields = new Slots(clazz.instanceSlotCount);
    }

    public boolean isInstanceOf(Class clazz) {
        return clazz.isAssignableFrom(clazz);
    }
}
