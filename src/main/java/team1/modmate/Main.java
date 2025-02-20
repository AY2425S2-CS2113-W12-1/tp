package team1.modmate;

import team1.modmate.mod.Mod;
import team1.modmate.log.Log;

import java.util.List;
import java.util.Scanner;

/**
 * Main class that runs the ModMate application. This application interacts with a list of mods,
 * allowing the user to enter commands and view or manipulate the mod data.
 * It supports logging, handling commands, and printing the mod list.
 */
public class Main {

    static String helpMessage = """
        Commands:
        -h: Display this help message
        exit: Exit the application
        printmods: Print the list of mods""";

    /**
     * The entry point of the ModMate application.
     * Processes command-line arguments, manages logging, and runs the main command loop.
     * It continuously prompts the user for commands and handles input based on predefined options.
     *
     * @param args Command-line arguments. The `--log` flag can be provided to enable logging.
     *             If provided, the flag should be followed by a boolean value ("true" or "false").
     */
    public static void main(String[] args) {

        for (int i = 0; i < args.length; i++) {
            if (args[i].equals("--log") && i + 1 < args.length) {
                Log.setLoggingEnabled(Boolean.parseBoolean(args[i + 1]));
            }
        }

        List<Mod> mods = ModTest.getMods();

        Log.printLog("Logging is enabled.");
        System.out.println("Welcome to ModMate!");

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("Enter command ('exit' to quit, '-h' for help): ");
            String input = scanner.nextLine().trim().toLowerCase();
            Log.saveLog("\n[MAIN]   Received input: " + input);
            switch (input) {
            case "exit" -> {
                Log.saveLog("[MAIN]   Exiting application.");
                System.out.println("Exiting...");
                scanner.close();
                return;
            }
            case "-h" -> {
                Log.saveLog("[MAIN]   Printing help message.");
                System.out.println(helpMessage);
            }
            case "printmods" -> {
                Log.saveLog("[MAIN]   Displaying mod list.");
                System.out.println("Mods list:");
                for (Mod mod : mods) {
                    System.out.println(mod);
                }
            }
            default -> Log.saveLog("[MAIN]   Command: " + input + " is invalid");
            }
        }
    }
}
