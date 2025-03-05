package modmate;

import java.util.List;
import java.util.Scanner;

import modmate.user.User;
import modmate.course.Course;
import modmate.log.Log;

/**
 * Main entry point for the ModMate application. It handles user input
 * and executes commands such as viewing courses, adding/removing courses
 * to/from timetables, and managing bookmarks.
 */
public class Main {

    static String helpMessage = """
        Commands:
        -h: Display this help message
        exit: Exit the application
        viewmod <course_code>: View details of a mod by its course code
        bookmark <course_code>: Bookmark a course for later reference
        bookmarks: View all bookmarked courses
        addmod <timetable> <course_name>: Add a mod to your list
        removemod <timetable> <course_name>: Remove a mod from your list
        createtimetable <timetable>: Create a new timetable
        timetable: <timetable> Display your mod timetable
        viewallmods: View all available mods
        searchmod <course_code_or_name>: Search for a mod by its code or name
        """;

    static List<Course> allCourses = SampleCourses.getCourses();

    /**
     * The main command loop of the application that processes user input
     * and executes corresponding actions based on the command received.
     *
     * @param args Command-line arguments (for logging configuration).
     */
    public static void main(String[] args) {

        // Check for logging configuration in command-line arguments
        for (int i = 0; i < args.length; i++) {
            if (args[i].equals("--log") && i + 1 < args.length) {
                Log.setLoggingEnabled(Boolean.parseBoolean(args[i + 1]));
            }
        }

        // Enable logging if specified and print welcome message
        Log.printLog("Logging is enabled.");
        System.out.println("Welcome to ModMate!");

        // Create a User object
        User currentUser = new User();

        // Create scanner object to read user input
        Scanner scanner = new Scanner(System.in);

        // Main command loop
        while (true) {
            System.out.println("Enter command ('exit' to quit, '-h' for help):");
            String input = scanner.nextLine().trim().toLowerCase();
            Log.saveLog("\n[MAIN]   Received input: " + input);

            String[] inputParts = input.split(" ", 3); // Split command and argument

            switch (inputParts[0]) {
            case "-h" -> printHelp();
            case "viewmod" -> viewCourse(inputParts);
            case "bookmark" -> bookmark(inputParts, currentUser);
            case "bookmarks" -> getBookmarks(currentUser);
            case "addmod" -> addCourseToTimetable(inputParts, currentUser);
            case "removemod" -> removeCourseFromTimetable(inputParts, currentUser);
            case "createtimetable" -> createTimetable(inputParts, currentUser);
            case "timetable" -> viewTimetable(inputParts, currentUser);
            case "viewallmods" -> viewAllCourses();
            case "searchmod" -> searchCourses(inputParts, currentUser);

            case "exit" -> {
                Log.saveLog("[MAIN]   Exiting application.");
                System.out.println("Exiting...");
                scanner.close();
                return;
            }
            default -> Log.saveLog("[MAIN]   Command: " + input + " is invalid");
            }
        }
    }

    /**
     * Prints the help message for available commands.
     */
    private static void printHelp() {
        Log.saveLog("[MAIN]   Printing help message.");
        System.out.println(helpMessage);
    }

    /**
     * Displays details of a course given its course code.
     *
     * @param inputParts The command input split into parts, where the second part is the course code.
     */
    private static void viewCourse(String[] inputParts) {
        if (!validateInputLength(inputParts, 2)) {
            return;
        }
        Log.saveLog("[MAIN]   Viewing mod details.");
        String courseCode = inputParts[1].trim();
        // TODO View details of a course
        // TODO Find in allCourses list if it exists
    }

    /**
     * Bookmarks a course for later reference.
     *
     * @param inputParts The command input split into parts, where the second part is the course code.
     * @param currentUser The user object representing the current user.
     */
    private static void bookmark(String[] inputParts, User currentUser) {
        if (!validateInputLength(inputParts, 2)) {
            return;
        }
        Log.saveLog("[MAIN]   Bookmarking mod.");
        String courseCode = inputParts[1].trim();
        // Bookmark a course for later reference
        // TODO Add course to user's bookmarks
    }

    /**
     * Displays all courses bookmarked by the user.
     *
     * @param currentUser The user object representing the current user.
     */
    private static void getBookmarks(User currentUser) {
        Log.saveLog("[MAIN]   Viewing bookmarks.");
        // View all bookmarked courses
        // TODO Print all courses in user's bookmarks
    }

    /**
     * Adds a course to the user's timetable.
     *
     * @param inputParts The command input split into parts, where the second part is the timetable name
     *                   and the third part is the course name.
     * @param currentUser The user object representing the current user.
     */
    private static void addCourseToTimetable(String[] inputParts, User currentUser) {
        if (!validateInputLength(inputParts, 3)) {
            return;
        }
        Log.saveLog("[MAIN]   Adding course to timetable.");
        String timetable = inputParts[1].trim();
        String courseName = inputParts[2].trim();

        // TODO Check if user has timetable by that name
        // TODO add course to timetable if it does
    }

    /**
     * Removes a course from the user's timetable.
     *
     * @param inputParts The command input split into parts, where the second part is the timetable name
     *                   and the third part is the course name.
     * @param currentUser The user object representing the current user.
     */
    private static void removeCourseFromTimetable(String[] inputParts, User currentUser) {
        if (!validateInputLength(inputParts, 3)) {
            return;
        }
        String timetable = inputParts[1].trim();
        String courseName = inputParts[2].trim();

        // TODO Check if user has timetable by that name
        // TODO remove course from timetable if it does
    }

    /**
     * Creates a new timetable for the user.
     *
     * @param inputParts The command input split into parts, where the second part is the timetable name.
     * @param currentUser The user object representing the current user.
     */
    private static void createTimetable(String[] inputParts, User currentUser) {
        if (!validateInputLength(inputParts, 2)) {
            return;
        }

        // TODO Check if timetable already exists for user
        Log.saveLog("[MAIN]   Creating timetable.");
        String timetableName = inputParts[1].trim();
        currentUser.addTimetable(timetableName);
        // TODO Implement this method in User
    }

    /**
     * Displays the user's timetable.
     *
     * @param inputParts The command input split into parts, where the second part is the timetable name.
     * @param currentUser The user object representing the current user.
     */
    private static void viewTimetable(String[] inputParts, User currentUser) {
        if (!validateInputLength(inputParts, 2)) {
            return;
        }
        Log.saveLog("[MAIN]   Displaying user's mod list.");
        String timetableName = inputParts[1].trim();
        System.out.println(currentUser.getTimetable(timetableName));
        // TODO Implement this method in User
    }

    /**
     * Displays all available courses.
     */
    private static void viewAllCourses() {
        Log.saveLog("[MAIN]   Viewing all mods.");
        for (Course course : allCourses) {
            System.out.println(course);
        }
    }

    /**
     * Searches for courses by course code or name.
     *
     * @param inputParts The command input split into parts, where the second part is the search term.
     * @param currentUser The user object representing the current user.
     */
    private static void searchCourses(String[] inputParts, User currentUser) {
        Log.saveLog("[MAIN]   User is searching for a mod.");
        List<Course> searchResults = getSearchResults(inputParts[1].trim());
        if (searchResults != null) {
            for (Course course : searchResults) {
                System.out.println(course);
            }
        } else {
            System.out.println("No mods found.");
        }
    }

    /**
     * Searches for courses that match the given search term.
     *
     * @param searchTerm The term to search for (course code or name).
     * @return A list of matching courses.
     */
    private static List<Course> getSearchResults(String searchTerm) {
        Log.saveLog("[MAIN]   Internally invoking search for " + searchTerm + ".");
        // TODO Search for courses that contain the search term in their course code or name
        // TODO Return a list of matching courses ordered by relevance
        return null;
    }

    /**
     * Validates that the input command has the expected number of arguments.
     *
     * @param inputParts The command input split into parts.
     * @param expectedLength The expected number of arguments.
     * @return True if the command has the expected number of arguments, false otherwise.
     */
    private static boolean validateInputLength(String[] inputParts, int expectedLength) {
        if (inputParts.length < expectedLength) {
            System.out.println("Invalid command. Please provide all necessary arguments.");
            return false;
        }
        return true;
    }
}
