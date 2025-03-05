package modmate.event;

/**
 * Represents a lecture event, which is a specific type of event that includes
 * information about the lecturer in addition to the general event details such as
 * the day, time, duration, cancelled weeks, and location.
 * <p>
 * A lecture is a scheduled event with a lecturer, and it extends the {@link Event} class.
 * </p>
 */
public class Lecture extends Event {
    private final String lecturer;

    /**
     * Constructs a Lecture object with the specified details.
     *
     * @param period the time period when the lecture occurs, including the day, start time, and duration
     * @param location the location where the lecture takes place
     * @param lecturer the name of the lecturer delivering the lecture
     */
    public Lecture(Period period, String location, String lecturer) {
        super(period, location);
        this.lecturer = lecturer;
    }

    /**
     * Gets the name of the lecturer for the lecture.
     *
     * @return the name of the lecturer
     */
    public String getLecturer() {
        return this.lecturer;
    }
}
