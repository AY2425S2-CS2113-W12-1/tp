package modmate.event;

/**
 * Represents a generic class that occurs on a specific day, at a specific time,
 * with a given duration, cancellation weeks, and location.
 * This class is abstract and can be extended to define specific types of events.
 */
public abstract class Event {
    private final Period period;
    private String location;

    /**
     * Constructs an Event object with the specified details.
     *
     * @param period the period when this event takes place
     * @param location the location where the event takes place
     */
    public Event(Period period, String location) {
        this.period = period;
        this.location = location;
    }

    /**
     * Gets the period when the event occurs.
     *
     * @return the period during which the event takes place
     */
    public Period getPeriod() {
        return this.period;
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
     * @param location the new location of the event
     */
    public void setLocation(String location) {
        this.location = location;
    }
}
