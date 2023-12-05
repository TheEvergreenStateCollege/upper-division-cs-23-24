package com.juniperGMVAD.app;

import java.util.Comparator;

public class CompareYear implements Comparator<YearValue>  {
    public int compare(YearValue yv1, YearValue yv2) {
        return yv1.year - yv2.year;
    }
}
