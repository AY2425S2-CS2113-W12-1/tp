package modmate.ui;

import java.util.Scanner;

import modmate.command.Command;
import modmate.command.system.ExitCommand;
import modmate.command.system.HelpCommand;
import modmate.exception.ApiException;
import modmate.exception.CommandException;
import modmate.exception.UserException;
import modmate.log.LogUtil;
import modmate.user.User;

public class UI {

    private static final LogUtil logUtil = new LogUtil(UI.class);

    public static void run(User user) {
        Scanner scanner = new Scanner(System.in);

        displayWelcomeMessage();
        boolean userIsExiting;

        do {
            userIsExiting = false;

            try {
                Input input = parseInput(scanner);
                executeCommand(input, user);
                userIsExiting = isUserExiting(input);
            } catch (CommandException | UserException | ApiException e) {
                System.out.println(e.getMessage());
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                logUtil.warning(e.getMessage());
            }
        } while (!userIsExiting);

        scanner.close();
    }

    private static void displayWelcomeMessage() {
        System.out.println("Welcome to ModMate!");
    }

    private static Input parseInput(Scanner scanner) {
        System.out.println("\nEnter command ('" + ExitCommand.CLI_REPRESENTATION
            + "' to quit, '"+ HelpCommand.CLI_REPRESENTATION + "' for help):");

        String inputString = scanner.nextLine().trim();
        logUtil.info("Received input: " + inputString);

        return InputParser.parse(inputString);
    }

    private static void executeCommand(Input input, User user) throws UserException, ApiException {
        Command command = CommandParser.parse(input);
        command.execute(user);
    }

    private static boolean isUserExiting(Input input) {
        return input.getCommand().equals(ExitCommand.CLI_REPRESENTATION);
    }

}
