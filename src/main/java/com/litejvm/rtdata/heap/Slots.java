package com.litejvm.rtdata.heap;

import com.litejvm.rtdata.Object;
import com.litejvm.rtdata.Slot;

public class Slots {
    Slot[] slots;

    public Slots(int length) {
        slots = new Slot[length];
        for (int i = 0; i < slots.length; i++) {
            slots[i] = new Slot();
        }
    }

    public void setInt(int index, int val) {
        slots[index].num = val;
    }

    public int getInt(int index) {
        return slots[index].num;
    }

    public void setFloat(int index, float val) {
        slots[index].num = Float.floatToIntBits(val);
    }

    public float getFloat(int index) {
        return Float.intBitsToFloat(slots[index].num);
    }

    public void setLong(int index, long val) {
        slots[index].num = (int) val;
        slots[index + 1].num = (int) (val >> 32);
    }

    public long getLong(int index) {
        long low = Integer.toUnsignedLong(slots[index].num);
        long high = Integer.toUnsignedLong(slots[index + 1].num);
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
        slots[index].ref = ref;
    }

    public Object getRef(int index) {
        return slots[index].ref;
    }
}
