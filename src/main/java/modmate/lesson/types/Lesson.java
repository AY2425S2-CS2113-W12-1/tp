package modmate.lesson.types;

import modmate.lesson.Period;

/**
 * Represents an abstract lesson lesson with a unique identifier, period, and venue.
 */
public abstract class Lesson {

    /**
     * The unique identifier for the lesson.
     */
    private final String id;

    /**
     * The period when this lesson takes place.
     */
    private final Period period;

    /**
     * The location where the lesson takes place.
     */
    private final String venue;

    /**
     * Constructs an lesson object with the specified details.
     *
     * @param period the period when this lesson takes place
     * @param venue  the location where the lesson takes place
     * @param id     the unique identifier for the lesson
     */
    public Lesson(Period period, String venue, String id) {
        this.period = period;
        this.venue = venue;
        this.id = id;
    }

    /**
     * Gets the period when the lesson occurs.
     *
     * @return the period during which the lesson takes place
     */
    public Period getPeriod() {
        return this.period;
    }

    /**
     * Gets the location of the lesson.
     *
     * @return the location of the lesson
     */
    public String getVenue() {
        return venue;
    }

    /**
     * Gets the class number of the lesson.
     *
     * @return the class number of the lesson
     */
    public String getId() {
        return id;
    }

}
