package dev.codewithfriends.IntervalScheduling;

import java.util.List;

public abstract class IntervalScheduler {

    public ScheduleResult createSchedule(List<Interval> inputIntervals) {
        long beginMillis = System.currentTimeMillis();
        Schedule schedule = _schedule(inputIntervals);
        long elapsedTime = System.currentTimeMillis() - beginMillis;
        int requestedIntervalCount = inputIntervals.size();
        int scheduledIntervalCount = schedule.size();
        Percentage coverage = new Percentage(
                requestedIntervalCount, scheduledIntervalCount
        );
        ScheduleStats stats = new ScheduleStats(elapsedTime, coverage);
        return new ScheduleResult(stats, schedule);
    }

    abstract Schedule _schedule(List<Interval> inputIntervals);

}