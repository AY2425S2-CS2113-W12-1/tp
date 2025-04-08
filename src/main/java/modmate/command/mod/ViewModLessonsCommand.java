package modmate.command.mod;

import modmate.command.Command;
import modmate.command.CommandUtil;
import modmate.command.util.Argument;
import modmate.download.nusmods.NUSModsAPI;
import modmate.exception.ApiException;
import modmate.exception.CommandException;
import modmate.log.LogUtil;
import modmate.mod.Mod;
import modmate.timetable.Lesson;
import modmate.ui.Input;
import modmate.user.ScheduleMod;
import modmate.user.User;

import java.util.List;

public class ViewModLessonsCommand extends Command {

    public static final String CLI_REPRESENTATION = "viewlessons";
    private static final LogUtil logUtil = new LogUtil(ViewAllModsCommand.class);

    private final Argument<String> modIdentifierArg;

    public ViewModLessonsCommand(Input input) {
        super(input);
        this.modIdentifierArg = new Argument<>(
                "mod code or name",
                input.getArgument(),
                "The code or name of the mod to view.",
                true);

        if (modIdentifierArg.getValue().isEmpty()) {
            throw new CommandException(this, "Mod code or name cannot be empty");
        }
    }

    @Override
    public String getDescription() {
        return "View available lessons for each mod, sorted by type.";
    }

    @Override
    public String getUsage() {
        return super.getUsage()
            + "  <mod code or name>: The code or name of the mod to view lessons for.";
    }

    @Override
    public String getSyntax() {
        return CommandUtil.buildSyntax(
            CLI_REPRESENTATION,
            List.of(modIdentifierArg)
        );
    }

    @Override
    public void execute(User currentUser) {
        logUtil.info("Viewing mod lessons for: " + modIdentifierArg.getValue());

        modIdentifierArg.getValue().ifPresent(
            identifier -> {
                logUtil.info("Viewing mod details for: " + identifier);
                try {
                    Mod mod = NUSModsAPI.modFromIdentifier(identifier);
                    ScheduleMod modSchedule = new ScheduleMod(mod);

                    for (String type: modSchedule.getLessonTypes()) {
                        System.out.println("[" + type + "]");
                        List<Lesson> sortedLessons = modSchedule.getLessonsForType(type)
                                .stream().sorted().toList();
                        for (Lesson lesson: sortedLessons) {
                            System.out.println("    " + lesson);
                        }
                    }
                    logUtil.info("Mod lessons displayed.");
                } catch (ApiException e) {
                    logUtil.severe("Failed to fetch mod details: " + e.getMessage());
                    System.out.println(e.getMessage());
                }
            });
    }


}
