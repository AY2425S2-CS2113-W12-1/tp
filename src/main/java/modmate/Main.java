package modmate;

import java.util.Scanner;

import modmate.command.CommandLine;
import modmate.download.nusmods.NUSModsAPI;
import modmate.log.LogUtil;
import modmate.user.User;

/**
 * Main entry point for the ModMate application. It handles user input
 * and executes commands such as viewing mods, adding/removing mods
 * to/from timetables, and managing bookmarks.
 */
public class Main {

    private static final LogUtil log = new LogUtil(Main.class);

    /**
     * The main command loop of the application that processes user input
     * and executes corresponding actions based on the command received.
     *
     * @param args Command-line arguments (for logging configuration).
     */
    public static void main(String[] args) {

        User currentUser = new User();

        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to ModMate!");

        while (true) {
            System.out.println("\nEnter command ('exit' to quit, '-h' for help):");

            String input = scanner.nextLine().trim();
            log.info("Received input: " + input);

            String[] inputParts = input.split(" ");
            String commandName = inputParts[0];

            // if exit command given
            if ("exit".equals(commandName)) {
                log.info("Exiting application.");
                System.out.println("Exiting...");
                break; // Signal to exit the application
            }

            // process rest of the other commands
            CommandLine.getCommand(commandName).ifPresentOrElse(
                    command -> command.execute(inputParts, currentUser),
                    () -> {
                        System.out.println("Invalid command \"" + commandName
                                + "\"! Please check your command again, or run -h for help.");
                        log.warning("Invalid command: " + input);
                    }
            );
        }

        scanner.close();
    }
}
