package com.juniperGMVAD.app;

public enum Indicator {
    // Canonical, given, non-calculated non-percentile indicators
    MVA("Manufacturing, value added (current US$)", "NV.IND.MANF.CD"),
    NNIPC("", ""),

    // User-defined, calculated indicators
    // Percentile
    PGMVA("Percentage of global manufacturing, value added (current US$)", true, true, Indicator.MVA, Country.WLD);

    public final String indicatorName;
    public final String indicatorCode;
    public final boolean isCalculated;
    public final boolean isPercentile;
    public final Indicator indicatorBasis;
    public final Country percentileBasis;

    /**
     * Constructor for canonical indicators which are given in data files, not calculated or percentile
     * @param indicatorName Canonical name of indicator
     * @param indicatorCode Canonical code of indicator
     */
    private Indicator(String indicatorName, String indicatorCode) {
        this.indicatorName   = indicatorName;
        this.indicatorCode   = indicatorCode;
        this.isCalculated    = false;
        this.isPercentile    = false;
        this.indicatorBasis  = null;
        this.percentileBasis = null;
    }

    /**
     * Constructor for calculated indicators (i.e., not given in data files). Has no canonical name or code and so name is user-defined and code is null
     * @param indicatorName User-defined name of indicator
     * @param isCalculated True if a calculated indicator
     * @param isPercentile True if is a percentage
     * @param indicatorBasis Given indicator this is based on
     * @param percentileBasis Country used as basis for percentage
     */
    private Indicator(String indicatorName, boolean isCalculated, boolean isPercentile, Indicator indicatorBasis, Country percentileBasis) {
        this.indicatorName   = indicatorName;
        this.indicatorCode   = null;
        this.isCalculated    = isCalculated;
        this.isPercentile    = isPercentile;
        this.indicatorBasis  = indicatorBasis;
        this.percentileBasis = percentileBasis;
    }
}
