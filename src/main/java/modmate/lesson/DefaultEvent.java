package modmate.lesson;

import modmate.lesson.types.Lesson;

/**
 * Represents a default event to be used in the case where the event type is not recognized.
 */
public class DefaultEvent extends Lesson {

    /**
     * Constructs a Default Event object with the specified details.
     *
     * @param period the period
     * @param location the location
     */
    public DefaultEvent(Period period, String location, String classNo) {
        super(period, location, classNo);
    }

}
