package modmate.lesson.types;

import modmate.lesson.Period;

/**
 * Represents a lecture.
 */
public class Lecture extends Lesson {

    /**
     * Constructs a Lecture object with the specified details.
     *
     * @param period the time period when the lecture occurs, including the day, start time, and duration
     * @param location the location where the lecture takes place
     * @param id the unique identifier for the lecture
     */
    public Lecture(Period period, String location, String id) {
        super(period, location, id);
    }

}
