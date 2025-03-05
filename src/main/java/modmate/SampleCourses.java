package modmate;

import modmate.course.Course;
import modmate.course.attribute.CourseAttributes;
import modmate.course.attribute.Faculty;
import modmate.course.attribute.SemesterAvailability;
import modmate.course.attribute.WeeklyWorkload;

import java.util.List;

/**
 * A utility class that provides a sample list of courses.
 * This class is used to generate a list of courses with details like course code, name, description, and prerequisites.
 */
public class SampleCourses {

    /**
     * Returns a list of sample courses.
     * <p>
     * Each course is represented by a {@link Course} object containing the following details:
     * <ul>
     *     <li>Course code (e.g., "CS1010")</li>
     *     <li>Course name (e.g., "Programming Methodology")</li>
     *     <li>Course description (e.g., "Introduction to programming")</li>
     *     <li>Prerequisites (List of Course objects)</li>
     * </ul>
     *
     * @return a list of {@link Course} objects representing sample courses
     */
    public static List<Course> getCourses() {
        // Faculties
        Faculty computingfaculty = new Faculty("School of Computing");
        Faculty businessfaculty = new Faculty("School of Business");

        // Semester availabilities
        List<SemesterAvailability> semesterAvailability1and2 = List.of(
                SemesterAvailability.SEMESTER_1,
                SemesterAvailability.SEMESTER_2);

        List<SemesterAvailability> semesterAvailability1 = List.of(
                SemesterAvailability.SEMESTER_1);

        List<SemesterAvailability> semesterAvailability2 = List.of(
                SemesterAvailability.SEMESTER_2);

        // Workloads
        WeeklyWorkload workload1 = new WeeklyWorkload(3, 6, 9, 12);
        WeeklyWorkload workload2 = new WeeklyWorkload(4, 8, 12, 16);
        WeeklyWorkload workload3 = new WeeklyWorkload(5, 10, 15, 20);

        // Courses
        // Programming Methodology I
        CourseAttributes caProgMeth = new CourseAttributes(
                computingfaculty,
                semesterAvailability1and2,
                4,
                true,
                null,
                workload1);

        Course progMeth1 = new Course(
                "Programming Methodology",
                "CS1010",
                "Introduction to programming",
                caProgMeth);

        // Programming Methodology II
        List<Course> prqProgMeth1 = List.of(progMeth1);
        CourseAttributes caProgMeth2 = new CourseAttributes(
                computingfaculty,
                semesterAvailability2,
                4,
                true,
                prqProgMeth1,
                workload1);

        Course progMeth2 = new Course(
                "Programming Methodology II",
                "CS2030",
                "Advanced programming",
                caProgMeth2);

        // Software Engineering
        CourseAttributes caSoftwareEngineering = new CourseAttributes(
                computingfaculty,
                semesterAvailability1,
                4,
                true,
                List.of(),
                workload2);

        Course softwareEng = new Course(
                "Software Engineering",
                "CS2103T",
                "Introduction to software engineering",
                caSoftwareEngineering);

        // Computer Networks
        CourseAttributes caComputerNetworks = new CourseAttributes(
                computingfaculty,
                semesterAvailability1and2,
                4,
                true,
                List.of(),
                workload3);

        Course computerNetworks = new Course(
                "Computer Networks",
                "CS2105",
                "Introduction to computer networks",
                caComputerNetworks);

        // Operating Systems
        CourseAttributes caOperatingSystems = new CourseAttributes(
                computingfaculty,
                semesterAvailability1and2,
                4,
                false,
                List.of(),
                workload2);

        Course operatingSystems = new Course(
                "Operating Systems",
                "CS2106",
                "Introduction to operating systems",
                caOperatingSystems);

        // Principles of Accounting
        CourseAttributes caPrinciplesOfAccounting = new CourseAttributes(
                businessfaculty,
                semesterAvailability1,
                4,
                true,
                List.of(),
                workload2);

        Course principlesOfAccounting = new Course(
                "Principles of Accounting",
                "ACC1002",
                "Introduction to accounting",
                caPrinciplesOfAccounting);

        return List.of(
                progMeth1,
                progMeth2,
                softwareEng,
                computerNetworks,
                operatingSystems,
                principlesOfAccounting
        );
    }
}
