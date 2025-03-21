package modmate;

import java.util.Scanner;

import modmate.user.User;
import modmate.download.nusmods.NUSModsAPI;
import modmate.log.Log;

import static modmate.CommandCenter.printHelp;
import static modmate.CommandCenter.viewMod;
import static modmate.CommandCenter.stringFromBetweenPartsXY;
import static modmate.CommandCenter.bookmark;
import static modmate.CommandCenter.getBookmarks;
import static modmate.CommandCenter.addModToTimetable;
import static modmate.CommandCenter.removeModFromTimetable;
import static modmate.CommandCenter.createTimetable;
import static modmate.CommandCenter.viewTimetable;
import static modmate.CommandCenter.viewAllMods;
import static modmate.CommandCenter.searchMods;
/**
 * Main entry point for the ModMate application. It handles user input
 * and executes commands such as viewing mods, adding/removing mods
 * to/from timetables, and managing bookmarks.
 */
public class Main {

    /**
     * The main command loop of the application that processes user input
     * and executes corresponding actions based on the command received.
     *
     * @param args Command-line arguments (for logging configuration).
     */
    public static void main(String[] args) {

        for (int i = 0; i < args.length; i++) {
            if (args[i].equals("--log") && i + 1 < args.length) {
                Log.setLoggingEnabled(Boolean.parseBoolean(args[i + 1]));
            } else if (args[i].equals("--startYear") && i + 1 < args.length) {
                int startYear = Integer.parseInt(args[i + 1]);
                CommandCenter.allModCodesAndNames = NUSModsAPI.fetchAllModCodes(startYear);
            }
        }
        Log.printLog("Logging is enabled.");

        User currentUser = new User();

        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to ModMate!");

        while (true) {
            System.out.println("\nEnter command ('exit' to quit, '-h' for help):");
            String input = scanner.nextLine().trim().toLowerCase();
            Log.saveLog("\n[MAIN]   Received input: " + input);

            String[] inputParts = input.split(" ");

            switch (inputParts[0]) {
            case "-h" -> printHelp();
            case "viewmod" -> viewMod(stringFromBetweenPartsXY(inputParts, 1));
            case "bookmark" -> bookmark(stringFromBetweenPartsXY(inputParts, 1), currentUser);
            case "bookmarks" -> getBookmarks(currentUser);
            case "addmod" -> addModToTimetable(
                    inputParts[1],
                    stringFromBetweenPartsXY(inputParts, 2),
                    currentUser
            );
            case "removemod" -> removeModFromTimetable(
                    inputParts[1],
                    stringFromBetweenPartsXY(inputParts, 2),
                    currentUser
            );
            case "createtimetable" -> createTimetable(stringFromBetweenPartsXY(inputParts, 1), currentUser);
            case "timetable" -> viewTimetable(stringFromBetweenPartsXY(inputParts, 1), currentUser);
            case "viewallmods" -> viewAllMods();
            case "searchmod" -> searchMods(stringFromBetweenPartsXY(inputParts, 1));

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
}
