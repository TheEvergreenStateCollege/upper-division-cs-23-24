package com.juniperGMVAD.app.YearValue;

import java.util.Comparator;

import com.juniperGMVAD.app.YearValue.YearValue;

public class SortByYear implements Comparator<YearValue>  {
    public int compare(YearValue yv1, YearValue yv2) {
        return yv1.year - yv2.year;
    }
}
