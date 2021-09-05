package com.litejvm.rtdata;

public class Slot {
    public int num;
    public Object ref;

    public static Slot copyFrom(Slot other) {
        Slot slot = new Slot();
        slot.num = other.num;
        slot.ref = other.ref;
        return slot;
    }

    @Override
    public String toString() {
        return "Slot{" +
                "num=" + num +
                ", ref=" + ref +
                '}';
    }
}
