package dev.codewithfriends.IntervalScheduling;

public class ScheduleInsertResult {

    private final boolean success;
    private final Interval conflicting;

    public boolean isSuccess() {
        return success;
    }

    public Interval isConflicting() {
        return conflicting;
    }

    /**
     * A result from attempting to insert an interval into a schedule.
     * @param success whether the insertion succeeded.
     * @param conflicting if success failed, the interval that conflicted with it.
     */
    public ScheduleInsertResult(boolean success, Interval conflicting) {
        this.success = success;
        this.conflicting = conflicting;
    }
}