package team1.modmate.mod.modattributes;

/**
 * Represents a school with a specific name.
 * This class stores the name of the school and provides methods to get and set it.
 */
public class School {
    private String school;

    /**
     * Constructs a School object with the specified school name.
     *
     * @param school the name of the school
     */
    public School(String school) {
        this.school = school;
    }

    /**
     * Gets the name of the school.
     *
     * @return the name of the school
     */
    public String getSchool() {
        return school;
    }

    /**
     * Sets the name of the school.
     *
     * @param school the name of the school to set
     */
    public void setSchool(String school) {
        this.school = school;
    }
}
