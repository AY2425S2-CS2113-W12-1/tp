package modmate.lesson.types;

import modmate.lesson.Period;

/**
 * Represents a tutorial.
 */
public class Tutorial extends Lesson {

    /**
     * Constructs a Tutorial object with the specified details.
     *
     * @param period the period when the tutorial occurs
     * @param location the location where the tutorial takes place
     * @param id the unique identifier for the tutorial
     */
    public Tutorial(Period period, String location, String id) {
        super(period, location, id);
    }

}
