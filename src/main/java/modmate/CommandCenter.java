package modmate;

import modmate.download.nusmods.NUSModsAPI;
import modmate.log.Log;
import modmate.mod.Mod;
import modmate.user.User;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public class CommandCenter {

    static Map<String, String> allModCodesAndNames = NUSModsAPI.fetchAllModCodes();

    static String helpMessage = """
        Commands:
        -h: Display this help message
        exit: Exit the application
        viewmod <mod code or name>: View details of a mod by its mod code or name
        bookmark <mod code or name>: Bookmark a mod for later reference
        bookmarks: View all bookmarked mods
        addmod <timetable> <mod code or name>: Add a mod to your list
        removemod <timetable> <mod code or name>: Remove a mod from your list
        createtimetable <timetable>: Create a new timetable
        timetable <timetable>: Display your mod timetable
        viewallmods: View all available mods
        """;
    // searchmod <mod code or name>: Search for a mod by its code or name
    // Add back when implemented

    /**
     * Prints the help message for available commands.
     */
    static void printHelp() {
        Log.saveLog("[MAIN]   Printing help message.");
        System.out.println(helpMessage);
    }

    /**
     * Helper method that searches for an exact matching mod by its code or name.
     *
     * @param modCodeOrNameGiven The code or name of the mod to search for.
     * @return The mod that matches the given code or name, or null if no match is found.
     */
    private static Optional<Mod> modFromCodeOrName(String modCodeOrNameGiven) {
        // First, check for a match with the module code (key)
        Optional<Map.Entry<String, String>> modCodeFound = allModCodesAndNames.entrySet()
                .stream()
                .filter(entry -> entry.getKey().equalsIgnoreCase(modCodeOrNameGiven)) // match by code
                .findFirst();

        // If no match was found by code, try matching by title (value)
        if (modCodeFound.isEmpty()) {
            modCodeFound = allModCodesAndNames.entrySet()
                    .stream()
                    .filter(entry -> entry.getValue().equalsIgnoreCase(modCodeOrNameGiven)) // match by title
                    .findFirst();
        }

        // If a match is found, retrieve mod details using the module code
        return modCodeFound.flatMap(entry ->
                NUSModsAPI.fetchModuleByCode(entry.getKey()));
    }



    /**
     * Helper method to extract a substring from the command input.
     *
     * @param inputParts The split command input.
     * @param x The starting index of the substring.
     * @return The concatenated string from index x to the end of the input.
     */
    static String stringFromBetweenPartsXY(String[] inputParts, int x) {
        return stringFromBetweenPartsXY(inputParts, x, inputParts.length);
    }

    /**
     * Helper method to extract a substring from the command input between two indices.
     *
     * @param inputParts The split command input.
     * @param x The starting index of the substring.
     * @param y The ending index of the substring.
     * @return The concatenated string between indices x and y in the input.
     */
    private static String stringFromBetweenPartsXY(String[] inputParts, int x, int y) {
        if (inputParts == null || inputParts.length == 0 || x < 0 || y > inputParts.length || x >= y) {
            return "";
        }

        StringBuilder sb = new StringBuilder();
        for (int i = x; i < y; i++) {
            sb.append(inputParts[i]);
            if (i < y - 1) {
                sb.append(" ");
            }
        }
        return sb.toString();
    }

    /**
     * Displays details of a mod given its mod code or name.
     *
     * @param inputCodeOrName The mod code or name to search for.
     */
    static void viewMod(String inputCodeOrName) {
        assert inputCodeOrName != null
                && !inputCodeOrName.trim().isEmpty() :
                "Mod code or name cannot be null or empty";
        Log.saveLog("[MAIN]   Viewing mod details for: " + inputCodeOrName);

        modFromCodeOrName(inputCodeOrName).ifPresentOrElse(mod -> {
            System.out.println(mod.getDetailedString());
            Log.saveLog("[MAIN]   Mod details displayed.");
        }, () -> {
            System.out.println("Mod '" + inputCodeOrName + "' not found.");
            Log.saveLog("[MAIN]   Mod '" + inputCodeOrName + "' not found.");
        });
    }


    /**
     * Bookmarks a mod for later reference.
     *
     * @param inputCodeOrName The mod code or name to bookmark.
     * @param currentUser The user object representing the current user.
     */
    static void bookmark(String inputCodeOrName, User currentUser) {
        Log.saveLog("[MAIN]   Bookmarking mod.");
        // Bookmark a mod for later reference

        modFromCodeOrName(inputCodeOrName).ifPresentOrElse(mod -> {
            currentUser.addBookmark(mod);
            System.out.println("Bookmark " + inputCodeOrName + " successfully added to your list.");
        }, () -> {
            Log.saveLog("[MAIN]   Course to bookmark not found.");
            System.out.println("Course with code '" + inputCodeOrName + "' not found.");
        });
    }

    /**
     * Displays all mods bookmarked by the user.
     *
     * @param currentUser The user object representing the current user.
     */
    static void getBookmarks(User currentUser) {
        Log.saveLog("[MAIN]   Viewing bookmarks.");

        // View all bookmarked courses
        List<Mod> bookmarks = currentUser.getBookmarks();

        System.out.println("You have " + bookmarks.size() + " bookmarks.");

        for (Mod mod : bookmarks) {
            System.out.println(mod);
        }
    }

    /**
     * Adds a mod to the user's timetable.
     *
     * @param timetable The name of the timetable to which the mod should be added.
     * @param inputCodeOrName The mod code or name to add to the timetable.
     * @param currentUser The user object representing the current user.
     */
    static void addModToTimetable(String timetable, String inputCodeOrName, User currentUser) {
        assert timetable != null && !timetable.trim().isEmpty() : "Timetable name cannot be null or empty";
        Log.saveLog("[MAIN]   Adding mod to timetable: " + timetable);

        modFromCodeOrName(inputCodeOrName).ifPresentOrElse(mod -> {
            currentUser.addModToTimetable(timetable, mod);
            Log.saveLog("[MAIN]   Mod " + mod.getCode() + " added to timetable " + timetable);
        }, () -> {
            System.out.println("Mod '" + inputCodeOrName + "' not found.");
            Log.saveLog("[MAIN]   Mod '" + inputCodeOrName + "' not found.");
        });
    }


    /**
     * Removes a mod from the user's timetable.
     *
     * @param timetable The name of the timetable from which the mod should be removed.
     * @param inputCodeOrName The mod code or name to remove from the timetable.
     * @param currentUser The user object representing the current user.
     */
    static void removeModFromTimetable(String timetable, String inputCodeOrName, User currentUser) {
        assert timetable != null && !timetable.trim().isEmpty() : "Timetable name cannot be null or empty";
        Log.saveLog("[MAIN]   Removing mod from timetable: " + timetable);

        modFromCodeOrName(inputCodeOrName).ifPresentOrElse(mod -> {
            currentUser.removeModFromTimetable(timetable, mod);
            Log.saveLog("[MAIN]   Mod " + mod.getCode() + " removed from timetable " + timetable);
        }, () -> {
            System.out.println("Mod '" + inputCodeOrName + "' not found.");
            Log.saveLog("[MAIN]   Mod '" + inputCodeOrName + "' not found.");
        });
    }


    /**
     * Creates a new timetable for the user.
     *
     * @param inputTimetableName The name of the new timetable to create.
     * @param currentUser The user object representing the current user.
     */
    static void createTimetable(String inputTimetableName, User currentUser) {
        assert inputTimetableName != null
                && !inputTimetableName.trim().isEmpty() :
                "Timetable name cannot be null or empty";
        Log.saveLog("[MAIN]   Creating timetable: " + inputTimetableName);

        if (currentUser.hasTimetable(inputTimetableName)) {
            System.out.println("Timetable with this name already exists.");
            Log.saveLog("[MAIN]   Timetable " + inputTimetableName + " already exists.");
        } else {
            currentUser.addTimetable(inputTimetableName.replaceAll("\\s+", ""));
            Log.saveLog("[MAIN]   Timetable " + inputTimetableName + " created successfully.");
        }
    }


    /**
     * Displays the user's timetable for a specific timetable name.
     *
     * @param inputTimetableName The name of the timetable to display.
     * @param currentUser The user object representing the current user.
     */
    static void viewTimetable(String inputTimetableName, User currentUser) {
        Log.saveLog("[MAIN]   Displaying user's mod list.");
        System.out.println(currentUser.getTimetable(inputTimetableName));
    }

    /**
     * Displays all available mods.
     */
    static void viewAllMods() {
        Log.saveLog("[MAIN]   Viewing all mods.");
        allModCodesAndNames.forEach((modCode, title) ->
                System.out.println(modCode + ": " + title));
    }


    /**
     * Searches for mods by mod code or name.
     *
     * @param inputSearchQuery The search term to search for (mod code or name).
     */
    static void searchMods(String inputSearchQuery) {
        Log.saveLog("[MAIN]   User is searching for a mod.");
        List<Mod> searchResults = getSearchResults(inputSearchQuery);
        if (searchResults != null) {
            for (Mod mod : searchResults) {
                System.out.println(mod);
            }
        } else {
            System.out.println("No mods found.");
        }
    }

    /**
     * Searches for mods that match the given search term.
     *
     * @param searchTerm The term to search for (mod code or name).
     * @return A list of matching mods.
     */
    private static List<Mod> getSearchResults(String searchTerm) {
        Log.saveLog("[MAIN]   Internally invoking search for " + searchTerm + ".");
        // Search inside Map allModCodesAndNames for matches
        // Will have to search through both halves of the map, code and name
        // If found, return list of getModFromAPIUsingCode(Code of Map pair found)
        // TODO Search for mods that contain the search term in their mod code or name
        // TODO Return a list of matching mods ordered by relevance
        return null;
    }
}
