package com.juniperGMVAD.app;

/**
 * A class for representing values associated with a range of years
 */
public class RangeValue {
    public int startYear;
    public int endYear;
    public Double value;

    public RangeValue(int startYear, int endYear, Double value) {
        this.startYear = startYear;
        this.endYear   = endYear;
        this.value     = value;
    }

    @Override
    public String toString() {
        return "(" + startYear + "-" + endYear + "), " + value + ")";
    }
}
