package team1.modmate.mod.events;

import java.util.List;

/**
 * Represents a lecture event, which is a specific type of event that includes
 * information about the lecturer in addition to the general event details such as
 * the day, time, duration, cancelled weeks, and location.
 */
public class Lecture extends Event {
    private String lecturer;

    /**
     * Constructs a Lecture object with the specified details.
     *
     * @param dayOfWeek the day of the week when the lecture occurs
     * @param startTime the start time of the lecture
     * @param duration the duration of the lecture in hours
     * @param cancelledWeeks a list of weeks when the lecture is cancelled
     * @param location the location where the lecture takes place
     * @param lecturer the name of the lecturer delivering the lecture
     */
    public Lecture(
            String dayOfWeek,
            String startTime,
            double duration,
            List<Integer> cancelledWeeks,
            String location,
            String lecturer) {
        super(dayOfWeek, startTime, duration, cancelledWeeks, location);
        this.lecturer = lecturer;
    }

    /**
     * Gets the name of the lecturer for the lecture.
     *
     * @return the name of the lecturer
     */
    public String getLecturer() {
        return lecturer;
    }

    /**
     * Sets the name of the lecturer for the lecture.
     *
     * @param lecturer the name of the lecturer to set
     */
    public void setLecturer(String lecturer) {
        this.lecturer = lecturer;
    }
}
