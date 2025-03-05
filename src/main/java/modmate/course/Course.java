package modmate.course;

import modmate.course.attribute.CourseAttributes;

/**
 * Represents a course with a specific name, code, description, and associated attributes.
 * This class stores the name, code, description, and attributes of the course
 * and provides methods to access each of these properties.
 */
public class Course {

    /**
     * The name of the course.
     */
    private final String name;

    /**
     * The code of the course (e.g., CS101).
     */
    private final String code;

    /**
     * A brief description of the course.
     */
    private final String description;

    /**
     * The attributes associated with the course, such as course level, credits, etc.
     */
    private final CourseAttributes attributes;

    /**
     * Constructs a new Course object with the specified name, code, description, and attributes.
     *
     * @param name The name of the course.
     * @param code The code of the course.
     * @param description A brief description of the course.
     * @param attributes The attributes associated with the course.
     */
    public Course(String name, String code, String description, CourseAttributes attributes) {
        this.name = name;
        this.code = code;
        this.description = description;
        this.attributes = attributes;
    }

    /**
     * Returns the name of the course.
     *
     * @return The name of the course.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Returns the code of the course.
     *
     * @return The code of the course.
     */
    public String getCode() {
        return code;
    }

    /**
     * Returns a brief description of the course.
     *
     * @return The description of the course.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns the attributes associated with the course.
     *
     * @return The attributes of the course.
     */
    public CourseAttributes getAttributes() {
        return attributes;
    }

    /**
     * Returns a string representation of the course.
     *
     * @return A string representation of the course.
     */
    public String toString() {
        return code + ": " + name + "\n    " + description + "\n    " + attributes.getFaculty();
    }
}
