package com.juniperGMVAD.app.YearValue;

import java.util.Comparator;

import com.juniperGMVAD.app.YearValue.YearValue;

public class SortByValue implements Comparator<YearValue>  {
    public int compare(YearValue yv1, YearValue yv2) {
        Double result = yv1.value - yv2.value;
        if (result > 0) {
            return 1;
        } else if (result == 0) {
            return 0;
        } else {
            return -1;
        }
    }
}
