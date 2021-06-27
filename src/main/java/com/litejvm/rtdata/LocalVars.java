package com.litejvm.rtdata;

import java.util.ArrayList;
import java.util.List;

public class LocalVars {

    private final List<Slot> slots;

    public LocalVars(int maxLocals) {
        this.slots = new ArrayList<>();
        for (int i = 0; i < maxLocals; i++) {
            this.slots.add(new Slot());
        }
    }

    public void setInt(int index, int val) {
        this.slots.get(index).num = val;
    }

    public int getInt(int index) {
        return this.slots.get(index).num;
    }

    public void setFloat(int index, float val) {
        this.slots.get(index).num = Float.floatToIntBits(val);
    }

    public float getFloat(int index) {
        return Float.intBitsToFloat(this.slots.get(index).num);
    }

    public void setLong(int index, long val) {
        this.slots.get(index).num = (int) val;
        this.slots.get(index + 1).num = (int) (val >> 32);
    }

    public long getLong(int index) {
        long low = Integer.toUnsignedLong(this.slots.get(index).num);
        long high = Integer.toUnsignedLong(this.slots.get(index + 1).num);
        return high << 32 | low;
    }

    public void setDouble(int index, double val) {
        long val_l = Double.doubleToLongBits(val);
        setLong(index, val_l);
    }

    public double getDouble(int index) {
        long val_l = getLong(index);
        return Double.longBitsToDouble(val_l);
    }

    public void setRef(int index, Object ref) {
        this.slots.get(index).ref = ref;
    }

    public Object getRef(int index) {
        return this.slots.get(index).ref;
    }

    @Override
    public String toString() {
        return "LocalVars{" +
                "slots=" + slots +
                '}';
    }
}
