package modmate.event;

/**
 * Represents a tutorial event, which is a specific type of event that includes
 * a teaching assistant (TA) responsible for the tutorial.
 */
public class Tutorial extends Event {
    private final String tutor;

    /**
     * Constructs a Tutorial object with the specified details, including the TA.
     *
     * @param period the period when the tutorial occurs
     * @param location the location where the tutorial takes place
     * @param tutor the name of the teaching assistant for the tutorial
     */
    public Tutorial(Period period, String location, String tutor) {
        super(period, location);
        this.tutor = tutor;
    }

    /**
     * Gets the name of the teaching assistant (TA) for the tutorial.
     *
     * @return the name of the teaching assistant
     */
    public String getTutor() {
        return this.tutor;
    }
}
