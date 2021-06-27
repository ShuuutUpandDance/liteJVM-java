package com.litejvm.instructions.comparisons.dcmp;

public interface DCMPInstruction {
    default boolean containsNaN(double... arr) {
        for (double v : arr) {
            if (Double.compare(v, Double.NaN) == 0) {
                return true;
            }
        }

        return false;
    }
}
