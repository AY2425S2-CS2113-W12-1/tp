package team1.modmate;

import team1.modmate.mod.Mod;
import team1.modmate.mod.events.Lecture;
import team1.modmate.mod.events.Tutorial;
import team1.modmate.mod.modattributes.Course;
import team1.modmate.mod.modattributes.School;

import java.util.Arrays;
import java.util.List;

/**
 * This class contains methods to test and display information about modules (mods).
 * It demonstrates how to create mods with various attributes such as name, code, and schedule.
 */
public class ModTest {

    /**
     * The main method that runs the test and prints out the list of mods.
     * It fetches the list of mods using the {@link #getMods()} method and prints each mod's name and code.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        List<Mod> mods = getMods();
        System.out.println("List of Mods:");
        for (Mod mod : mods) {
            System.out.println(mod.getName() + " (" + mod.getCode() + ")");
        }
    }

    /**
     * Creates and returns a list of mods with associated events (lectures and tutorials).
     * Each mod includes details such as its name, code, course, school, and schedule.
     *
     * @return A list of {@link Mod} objects.
     */
    public static List<Mod> getMods() {
        List<Integer> cancelledWeeks = Arrays.asList(5, 6);

        // Creating sample events for lectures and tutorials
        Lecture lecture1 = new Lecture("Monday", "10:00", 2.0, cancelledWeeks, "Room A", "Dr. Smith");
        Tutorial tutorial1 = new Tutorial("Wednesday", "14:00", 1.0, cancelledWeeks, "Room B", "Mr. Lee");
        Lecture lecture2 = new Lecture("Friday", "09:00", 2.0, cancelledWeeks, "Room C", "Dr. Taylor");

        // Creating sample course and school
        Course course1 = new Course("Computer Science");
        School school1 = new School("School of Computing");

        // Creating mod objects with various attributes
        Mod mod1 = new Mod("CS1010", "Introduction to Programming", course1, school1, 4,
                Mod.SemesterAvailability.SEM1, true, "Basic programming concepts",
                List.of("None"), 3, 1, 2, 1, Arrays.asList(lecture1, tutorial1));

        Mod mod2 = new Mod("MA1101", "Calculus I", course1, school1, 5,
                Mod.SemesterAvailability.SEM1, true, "Introduction to calculus",
                List.of("None"), 4, 1, 2, 2, List.of(lecture2));

        Mod mod3 = new Mod("CS1231", "Discrete Mathematics", course1, school1, 4,
                Mod.SemesterAvailability.BOTH, true, "Mathematical foundations of computer science",
                List.of("None"), 3, 2, 2, 2, Arrays.asList(lecture1, lecture2));

        // Returning a list of all mods
        return Arrays.asList(mod1, mod2, mod3);
    }
}
