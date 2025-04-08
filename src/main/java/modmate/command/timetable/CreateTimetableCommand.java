package modmate.command.timetable;

import modmate.command.Command;
import modmate.command.CommandUtil;
import modmate.command.util.Argument;
import modmate.exception.CommandException;
import modmate.exception.UserException;
import modmate.log.LogUtil;
import modmate.ui.Input;
import modmate.user.User;

import java.util.List;

public class CreateTimetableCommand extends Command {

    public static final String CLI_REPRESENTATION = "createtimetable";

    private static final LogUtil logUtil = new LogUtil(CreateTimetableCommand.class);

    private final Argument<String> timetableNameArg;

    public CreateTimetableCommand(Input input) {
        super(input);
        this.timetableNameArg = new Argument<>(
                "timetable name",
                input.getArgument(),
                "The name of the timetable",
                true
        );

        if(timetableNameArg.getValue().isEmpty()) {
            throw new CommandException(this, "Timetable name cannot be empty");
        }
    }

    @Override
    public String getSyntax() {
        return CommandUtil.buildSyntax(
            CLI_REPRESENTATION,
            List.of(timetableNameArg)
        );
    }

    @Override
    public String getDescription() {
        return "Create a new timetable.";
    }

    @Override
    public String getUsage() {
        return super.getUsage() + "  <timetable>: The name of the timetable to create.";
    }

    @Override
    public void execute(User user) throws UserException {
        String inputTimetableName = input.getArgument();

        if (user.hasTimetable(inputTimetableName)) {
            System.out.println("Timetable with this name already exists.");
            logUtil.warning("Timetable " + inputTimetableName + " already exists.");
            throw new UserException("Timetable with this name already exists.");
        }

        logUtil.info("Creating timetable: " + inputTimetableName);
        user.addTimetable(inputTimetableName.replaceAll("\\s+", ""));
        logUtil.info("Timetable " + inputTimetableName + " created successfully.");
    }

}
