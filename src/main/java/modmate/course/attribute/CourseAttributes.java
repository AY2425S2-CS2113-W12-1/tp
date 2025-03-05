package modmate.course.attribute;

import java.util.List;

import modmate.course.Course;

/**
 * Represents various attributes of a course, including faculty, semester availability,
 * unit count, grading status, prerequisites, and workload.
 */
public class CourseAttributes {
    private Faculty faculty;
    private List<SemesterAvailability> semesterAvailability;
    private int units;
    private boolean isGraded;
    private List<Course> prerequisites;
    private WeeklyWorkload workload;

    /**
     * Constructs a CourseAttributes object with the given attributes.
     *
     * @param faculty               The faculty offering the course.
     * @param semesterAvailability  A list indicating the semesters in which the course is available.
     * @param units                 The number of units the course is worth.
     * @param isGraded              Whether the course is graded or pass/fail.
     * @param prerequisites         A list of prerequisite courses required for enrollment.
     * @param workload              The expected weekly workload for the course.
     */
    public CourseAttributes(Faculty faculty, List<SemesterAvailability> semesterAvailability,
                            int units, boolean isGraded, List<Course> prerequisites, WeeklyWorkload workload) {
        this.faculty = faculty;
        this.semesterAvailability = semesterAvailability;
        this.units = units;
        this.isGraded = isGraded;
        this.prerequisites = prerequisites;
        this.workload = workload;
    }

    /**
     * Gets the faculty offering the course.
     *
     * @return The faculty of the course.
     */
    public Faculty getFaculty() {
        return faculty;
    }

    /**
     * Sets the faculty offering the course.
     *
     * @param faculty The faculty to set.
     */
    public void setFaculty(Faculty faculty) {
        this.faculty = faculty;
    }

    /**
     * Gets the list of semesters in which the course is available.
     *
     * @return A list of SemesterAvailability objects.
     */
    public List<SemesterAvailability> getSemesterAvailability() {
        return semesterAvailability;
    }

    /**
     * Sets the list of semesters in which the course is available.
     *
     * @param semesterAvailability A list of SemesterAvailability objects.
     */
    public void setSemesterAvailability(List<SemesterAvailability> semesterAvailability) {
        this.semesterAvailability = semesterAvailability;
    }

    /**
     * Gets the number of units the course is worth.
     *
     * @return The number of units.
     */
    public int getUnits() {
        return units;
    }

    /**
     * Sets the number of units the course is worth.
     *
     * @param units The number of units to set.
     */
    public void setUnits(int units) {
        this.units = units;
    }

    /**
     * Checks if the course is graded.
     *
     * @return true if the course is graded, false otherwise.
     */
    public boolean isGraded() {
        return isGraded;
    }

    /**
     * Sets whether the course is graded.
     *
     * @param isGraded true if the course should be graded, false otherwise.
     */
    public void setGraded(boolean isGraded) {
        this.isGraded = isGraded;
    }

    /**
     * Gets the list of prerequisite courses required for enrollment.
     *
     * @return A list of prerequisite courses.
     */
    public List<Course> getPrerequisites() {
        return prerequisites;
    }

    /**
     * Sets the list of prerequisite courses required for enrollment.
     *
     * @param prerequisites A list of prerequisite courses.
     */
    public void setPrerequisites(List<Course> prerequisites) {
        this.prerequisites = prerequisites;
    }

    /**
     * Gets the expected weekly workload for the course.
     *
     * @return The WeeklyWorkload object representing the workload.
     */
    public WeeklyWorkload getWorkload() {
        return workload;
    }

    /**
     * Sets the expected weekly workload for the course.
     *
     * @param workload The WeeklyWorkload object to set.
     */
    public void setWorkload(WeeklyWorkload workload) {
        this.workload = workload;
    }
}
