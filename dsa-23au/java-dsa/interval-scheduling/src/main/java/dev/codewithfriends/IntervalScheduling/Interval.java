package dev.codewithfriends.IntervalScheduling;

import java.util.Comparator;

public final class Interval {

    private final long startTime;
    private final long endTime;
    private final String description;
    private final String requesterId;

    public final static class StartTimeComparator
            implements java.util.Comparator<Interval> {

        public int compare(Interval a, Interval b) {

            return Integer.signum((int)(a.startTime - b.startTime));
        }
    }

    public final static class EndTimeComparator
            implements java.util.Comparator<Interval> {

        public int compare(Interval a, Interval b) {

            return Integer.signum((int)(a.endTime - b.endTime));
        }
    }

    public Interval(
            long startTime,
            long endTime,
            String description,
            String requesterId
    ) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.description = description;
        this.requesterId = requesterId;
    }

    public boolean overlapsWith(Interval o) {
        if (o == null) {
            return false;
        }
        return Long.signum(this.startTime - o.endTime) ==
                Long.signum(o.startTime - this.endTime);
    /*
        (this.startTime > o.startTime) &&
        (this.startTime < o.endTime)
      ) || (
        (this.endTime > o.startTime) &&
        (this.endTime < o.endTime)
      );
     */
    }

    public String toString() {
        return String.format("(%d,%d)", this.startTime, this.endTime);
    }

    public long getStartTime() {
        return this.startTime;
    }

    public long getEndTime() {
        return this.endTime;
    }

    public String getDescription() {
        return this.description;
    }

    public String getRequesterId() {
        return this.requesterId;
    }

}