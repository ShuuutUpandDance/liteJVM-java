package com.litejvm.instructions.comparisons.fcmp;

public interface FCMPInstruction {
    default boolean containsNaN(float... arr) {
        for (float v : arr) {
            if (Float.compare(v, Float.NaN) == 0) {
                return true;
            }
        }
        return false;
    }
}
