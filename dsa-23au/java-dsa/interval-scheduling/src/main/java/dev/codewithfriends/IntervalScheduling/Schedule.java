package dev.codewithfriends.IntervalScheduling;

import java.util.Optional;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.List;

/**
 * A schedule that can be built up interval by interval by
 * an IntervalScheduler.
 */
public final class Schedule {

    private final TreeSet<Interval> intervalsByStartTime;
    private final TreeSet<Interval> intervalsByEndTime;

    public Schedule() {
        intervalsByStartTime = new TreeSet<Interval>(new Interval.StartTimeComparator());
        intervalsByEndTime = new TreeSet<Interval>(new Interval.StartTimeComparator());
    }

    /**
     * @return the Intervals, which do not overlap,
     */
    public List<Interval> getSortedSchedule;

    public int size() {
        return intervalsByStartTime.size();
    }

    public Optional<Interval> insertInterval(Interval newInterval) {

        // greatest element strictly less than newInterval by end time
        Interval lower = intervalsByEndTime.lower(newInterval);
        if (lower == null) {
            // new interval is the least element by end time, therefore by start time as well
            intervalsByEndTime.add(newInterval);
            intervalsByStartTime.add(newInterval);
            assert intervalsByEndTime.first() == newInterval;
            assert intervalsByStartTime.first() == newInterval;
            return Optional.empty();
        } else if (lower.overlapsWith(newInterval)) {
            // new interval conflicts with the next previous interval
            return Optional.of(lower);
        }

        // least element strictly greater than newInterval by start time
        Interval higher = intervalsByStartTime.higher(newInterval);
        if (higher == null) {
            // new interval is the highest by start time, therefore by end time as well
            intervalsByEndTime.add(newInterval);
            intervalsByStartTime.add(newInterval);
            assert intervalsByEndTime.last() == newInterval;
            assert intervalsByStartTime.last() == newInterval;
            return Optional.empty();
        } else if (higher.overlapsWith(newInterval)) {
            return Optional.of(higher);
        }

        return Optional.empty();
    }
}