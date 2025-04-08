package modmate.command.mod;

import modmate.command.Command;
import modmate.command.util.Argument;
import modmate.download.nusmods.NUSModsAPI;
import modmate.exception.ApiException;
import modmate.exception.CommandException;
import modmate.log.LogUtil;
import modmate.mod.Mod;
import modmate.ui.Input;
import modmate.user.User;

import java.util.Arrays;

public class SetModLessonCommand extends Command {

    public static final String CLI_REPRESENTATION = "setlesson";

    private static final LogUtil logUtil = new LogUtil(ViewAllModsCommand.class);

    private final Argument<String> timetableNameArg;
    private final Argument<String> modIdentifierArg;
    private final Argument<String> lessonTypeArg;
    private final Argument<String> lessonIdArg;

    public SetModLessonCommand(Input input) {
        super(input);
        String[] arguments = input.getArgument().split(" ");

        switch (arguments.length) {
        case 0:
            throw new CommandException(this, "Timetable name cannot be empty");
        case 1:
            throw new CommandException(this, "Mod name/code name cannot be empty");
        case 2:
            throw new CommandException(this, "Lesson type cannot be empty");
        case 3:
            throw new CommandException(this, "Lesson ID cannot be empty");
        default:
            String timetableName = String.join(
                    " ",
                    Arrays.copyOfRange(arguments, 0, arguments.length - 3));
            String modIdentifier = arguments[arguments.length - 3];
            String lessonType = arguments[arguments.length - 2];
            String lessonId = arguments[arguments.length - 1];

            this.timetableNameArg = new Argument<>(
                    "timetable name",
                    timetableName,
                    "The name of the timetable to display",
                    true
            );
            this.modIdentifierArg = new Argument<>(
                    "mod name or code",
                    modIdentifier,
                    "The mod in the timetable",
                    true
            );
            this.lessonTypeArg = new Argument<>(
                    "timetable type",
                    lessonType,
                    "The lesson type for the course",
                    true
            );
            this.lessonIdArg = new Argument<>(
                    "timetable type",
                    lessonId,
                    "The lesson to replace with",
                    true
            );

            if (timetableNameArg.getValue().isEmpty()) {
                throw new CommandException(this, "Timetable name cannot be empty");
            }
            if (modIdentifierArg.getValue().isEmpty()) {
                throw new CommandException(this, "Mod name/code name cannot be empty");
            }
            if (lessonTypeArg.getValue().isEmpty()) {
                throw new CommandException(this, "Lesson type cannot be empty");
            }
            if (lessonIdArg.getValue().isEmpty()) {
                throw new CommandException(this, "Lesson ID cannot be empty");
            }

        }

    }

    @Override
    public String getDescription() {
        return "Set lesson for each mod, by type.";
    }

    @Override
    public String getUsage() {
        return super.getUsage() + "  <timetable>: The name of the timetable.\n"
             + "  <mod code or name>: The code or name of the mod.\n"
             + "  <lesson type>: The type of lesson (e.g., Lecture, Tutorial).\n"
             + "  <lesson id>: The ID of the lesson to set.";
    }

    @Override
    public String getSyntax() {
        return CLI_REPRESENTATION + " <timetable> <mod code or name> <lesson type> <lesson id>";
    }

    @Override
    public void execute(User currentUser) {

        if (!currentUser.hasTimetable(timetableNameArg.getValue().orElse(""))) {
            System.out.println("Timetable \"" + timetableNameArg.getValue() + "\" not found.");
            logUtil.info("Timetable '"
                    + timetableNameArg.getValue()
                    + "' not found while attempting to setting lesson for mod "
                    + modIdentifierArg.getValue()
                    + ".");
            return;
        }


        modIdentifierArg.getValue().ifPresent(
            identifier -> {
                logUtil.info("Viewing mod details for: " + identifier);
                try {
                    Mod mod = NUSModsAPI.modFromIdentifier(identifier);
                    currentUser.setLessonOnTimetable(
                            timetableNameArg.getValue().orElse(""),
                            mod,
                            lessonTypeArg.getValue().orElse(""),
                            lessonIdArg.getValue().orElse(""));
                } catch (ApiException e) {
                    logUtil.severe("Failed to fetch mod details: " + e.getMessage());
                    System.out.println(e.getMessage());
                }
            });
    }

}
