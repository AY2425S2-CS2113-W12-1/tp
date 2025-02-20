package team1.modmate.mod.modattributes;

/**
 * Represents a course with a specific name.
 * This class stores the name of the course and provides methods to get and set it.
 */
public class Course {
    private String course;

    /**
     * Constructs a Course object with the specified course name.
     *
     * @param course the name of the course
     */
    public Course(String course) {
        this.course = course;
    }

    /**
     * Gets the name of the course.
     *
     * @return the name of the course
     */
    public String getCourse() {
        return course;
    }

    /**
     * Sets the name of the course.
     *
     * @param course the name of the course to set
     */
    public void setCourse(String course) {
        this.course = course;
    }
}
