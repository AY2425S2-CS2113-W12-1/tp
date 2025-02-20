package team1.modmate.mod.events;

import java.util.List;

/**
 * Represents a generic event that occurs on a specific day, at a specific time,
 * with a given duration, cancellation weeks, and location.
 * This class is abstract and can be extended to define specific types of events.
 */
public abstract class Event {
    private String dayOfWeek;
    private String startTime;
    private double duration;
    private List<Integer> cancelledWeeks;
    private String location;

    /**
     * Constructs an Event object with the specified details.
     *
     * @param dayOfWeek the day of the week when the event occurs
     * @param startTime the start time of the event
     * @param duration the duration of the event in hours
     * @param cancelledWeeks a list of weeks when the event is cancelled
     * @param location the location where the event takes place
     */
    public Event(String dayOfWeek, String startTime, double duration, List<Integer> cancelledWeeks, String location) {
        this.dayOfWeek = dayOfWeek;
        this.startTime = startTime;
        this.duration = duration;
        this.cancelledWeeks = cancelledWeeks;
        this.location = location;
    }

    /**
     * Gets the day of the week when the event occurs.
     *
     * @return the day of the week
     */
    public String getDayOfWeek() {
        return dayOfWeek;
    }

    /**
     * Sets the day of the week when the event occurs.
     *
     * @param dayOfWeek the day of the week to set
     */
    public void setDayOfWeek(String dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    /**
     * Gets the start time of the event.
     *
     * @return the start time of the event
     */
    public String getStartTime() {
        return startTime;
    }

    /**
     * Sets the start time of the event.
     *
     * @param startTime the start time to set
     */
    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    /**
     * Gets the duration of the event in hours.
     *
     * @return the duration of the event
     */
    public double getDuration() {
        return duration;
    }

    /**
     * Sets the duration of the event in hours.
     *
     * @param duration the duration to set
     */
    public void setDuration(double duration) {
        this.duration = duration;
    }

    /**
     * Gets the list of weeks when the event is cancelled.
     *
     * @return the list of cancelled weeks
     */
    public List<Integer> getCancelledWeeks() {
        return cancelledWeeks;
    }

    /**
     * Sets the list of weeks when the event is cancelled.
     *
     * @param cancelledWeeks the cancelled weeks to set
     */
    public void setCancelledWeeks(List<Integer> cancelledWeeks) {
        this.cancelledWeeks = cancelledWeeks;
    }

    /**
     * Gets the location of the event.
     *
     * @return the location of the event
     */
    public String getLocation() {
        return location;
    }

    /**
     * Sets the location of the event.
     *
     * @param location the location to set
     */
    public void setLocation(String location) {
        this.location = location;
    }
}
