package com.litejvm.rtdata;

import java.util.ArrayList;
import java.util.List;

public class OperandStack {
    private int size = 0;
    private List<Slot> slots;

    public OperandStack(int maxStack) {
        this.slots = new ArrayList<>();
        for (int i = 0; i < maxStack; i++) {
            this.slots.add(new Slot());
        }
    }

    public void pushInt(int val) {
        this.slots.get(this.size++).num = val;
    }

    public int popInt() {
        return this.slots.get(--this.size).num;
    }

    public void pushFloat(float val) {
        this.slots.get(this.size++).num = Float.floatToIntBits(val);
    }

    public float popFloat() {
        return Float.intBitsToFloat(this.slots.get(--this.size).num);
    }

    public void pushLong(long val) {
        this.slots.get(this.size).num = (int) val;
        this.slots.get(this.size + 1).num = (int) (val >> 32);
        this.size += 2;
    }

    public long popLong() {
        this.size -= 2;
        long low = Integer.toUnsignedLong(this.slots.get(this.size).num);
        long high = Integer.toUnsignedLong(this.slots.get(this.size + 1).num);
        return high << 32 | low;
    }

    public void pushDouble(double val) {
        long val_l = Double.doubleToLongBits(val);
        pushLong(val_l);
    }

    public double popDouble() {
        long val_l = popLong();
        return Double.longBitsToDouble(val_l);
    }

    public void pushRef(Object ref) {
        this.slots.get(this.size++).ref = ref;
    }

    public Object popRef() {
        this.size--;
        Object ref = this.slots.get(this.size).ref;
        this.slots.get(this.size).ref = null;
        return ref;
    }
}
