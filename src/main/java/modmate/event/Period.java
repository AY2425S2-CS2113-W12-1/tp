package modmate.event;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;

/**
 * Represents a period of time within a week, including the day, start and end times, and
 * the occurrence of this period across different weeks.
 */
public class Period {

    /**
     * The day of the week this period occurs on.
     */
    private DayOfWeek day;

    /**
     * The start time of the period.
     */
    private LocalTime startTime;

    /**
     * The end time of the period.
     */
    private LocalTime endTime;

    /**
     * A map representing whether this period occurs in a particular week.
     * The key is the week number, and the value indicates whether the period occurs in that week.
     */
    private HashMap<Integer, Boolean> occurrenceByWeek;

    /**
     * Private constructor to initialize a period with a specified day, start time, end time,
     * and a map of occurrences by week.
     *
     * @param day The day of the week the period occurs.
     * @param startTime The start time of the period.
     * @param endTime The end time of the period.
     * @param occurrenceByWeek A map indicating the occurrence of the period in specific weeks.
     */
    private Period(DayOfWeek day, LocalTime startTime, LocalTime endTime, HashMap<Integer, Boolean> occurrenceByWeek) {
        this.day = day;
        this.startTime = startTime;
        this.endTime = endTime;
        this.occurrenceByWeek = occurrenceByWeek;
    }

    /**
     * Gets the duration of the period in minutes.
     *
     * @return The duration in minutes between the start and end times.
     */
    public long getDuration() {
        return this.startTime.until(endTime, ChronoUnit.MINUTES);
    }

    /**
     * Gets the day of the week the period occurs on.
     *
     * @return The day of the week.
     */
    public DayOfWeek getDay() {
        return day;
    }

    /**
     * Gets the start time of the period.
     *
     * @return The start time of the period.
     */
    public LocalTime getStartTime() {
        return startTime;
    }

    /**
     * Gets the end time of the period.
     *
     * @return The end time of the period.
     */
    public LocalTime getEndTime() {
        return endTime;
    }

    /**
     * Gets the map of occurrences for this period by week.
     *
     * @return A map where the key is the week number, and the value indicates whether the
     *         period occurs in that week.
     */
    public HashMap<Integer, Boolean> getOccurrenceByWeek() {
        return occurrenceByWeek;
    }
}
