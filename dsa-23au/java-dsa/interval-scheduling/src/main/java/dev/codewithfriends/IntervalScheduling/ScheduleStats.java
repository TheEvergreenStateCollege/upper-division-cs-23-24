package dev.codewithfriends.IntervalScheduling;

public class ScheduleStats {

    private final long elapsedTime;
    private final Percentage coverage;

    public ScheduleStats(long elapsedTime, Percentage coverage) {
        this.elapsedTime = elapsedTime;
        this.coverage = coverage;
    }

    public long getElapsedTime() {
        return elapsedTime;
    }

    public Percentage getCoverage() {
        return coverage;
    }
}
