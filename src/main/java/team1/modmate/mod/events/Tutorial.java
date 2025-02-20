package team1.modmate.mod.events;

import java.util.List;

/**
 * Represents a tutorial event, which is a specific type of event that includes
 * a teaching assistant (TA) responsible for the tutorial.
 */
public class Tutorial extends Event {
    private String ta;

    /**
     * Constructs a Tutorial object with the specified details, including the TA.
     *
     * @param dayOfWeek the day of the week when the tutorial occurs
     * @param startTime the start time of the tutorial
     * @param duration the duration of the tutorial in hours
     * @param cancelledWeeks a list of weeks when the tutorial is cancelled
     * @param location the location where the tutorial takes place
     * @param ta the name of the teaching assistant for the tutorial
     */
    public Tutorial(String dayOfWeek,
                    String startTime,
                    double duration,
                    List<Integer> cancelledWeeks,
                    String location,
                    String ta) {
        super(dayOfWeek, startTime, duration, cancelledWeeks, location);
        this.ta = ta;
    }

    /**
     * Gets the name of the teaching assistant (TA) for the tutorial.
     *
     * @return the name of the teaching assistant
     */
    public String getTa() {
        return ta;
    }

    /**
     * Sets the name of the teaching assistant (TA) for the tutorial.
     *
     * @param ta the name of the teaching assistant to set
     */
    public void setTa(String ta) {
        this.ta = ta;
    }
}
