package modmate.command.system;

import modmate.command.Command;
import modmate.command.CommandUtil;
import modmate.log.LogUtil;
import modmate.ui.Input;
import modmate.user.User;

public class ExitCommand extends Command {

    public static final String CLI_REPRESENTATION = "exit";

    private static final LogUtil logUtil = new LogUtil(ExitCommand.class);

    public ExitCommand(Input input) {
        super(input);
    }

    @Override
    public String getSyntax() {
        return CommandUtil.buildSyntax(CLI_REPRESENTATION);
    }

    @Override
    public String getDescription() {
        return "Exit the application.";
    }

    @Override
    public void execute(User user) {
        logUtil.info("Exiting application.");
        System.out.println("Exiting...");
    }

}
