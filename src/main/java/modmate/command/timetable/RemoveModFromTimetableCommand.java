package modmate.command.timetable;

import modmate.command.Command;
import modmate.exception.ApiException;
import modmate.exception.CommandException;
import modmate.exception.UserException;
import modmate.log.LogUtil;
import modmate.mod.Mod;
import modmate.ui.Input;
import modmate.user.User;

import java.util.List;
import java.util.Optional;

public class RemoveModFromTimetableCommand extends Command {

    public static final String CLI_REPRESENTATION = "removemod";

    private static final LogUtil logUtil = new LogUtil(RemoveModFromTimetableCommand.class);

    public RemoveModFromTimetableCommand(Input input) {
        super(input);
    }

    @Override
    public String getSyntax() {
        return CLI_REPRESENTATION + " <timetable name> <mod code or name>";
    }

    @Override
    public String getDescription() {
        return "Remove a mod from your timetable.";
    }

    @Override
    public String getUsage() {
        return super.getUsage()
                + "  <timetable name>: The name of the timetable.\n"
                + "  <mod code or name>: The code or name of the mod to remove.";
    }

    @Override
    public void execute(User currentUser) throws CommandException, UserException, ApiException {
        String[] args = input.getArgument().split(" ", 2);
        if (args.length < 2) {
            System.out.println("Usage: " + getSyntax());
            return;
        }

        String timetableName = args[0];
        String modQuery = args[1];
        String upperQuery = modQuery.toUpperCase();

        if (!currentUser.hasTimetable(timetableName)) {
            System.out.println("Timetable \"" + timetableName + "\" not found.");
            logUtil.warning("Timetable \"" + timetableName + "\" not found.");
            return;
        }

        List<Mod> modsInTimetable = currentUser.getTimetable(timetableName).getMods();

        Optional<Mod> modByCode = modsInTimetable.stream()
                .filter(m -> m.getCode().equalsIgnoreCase(upperQuery))
                .findFirst();

        if (modByCode.isPresent()) {
            currentUser.removeModFromTimetable(timetableName, modByCode.get());
            return;
        }

        List<Mod> nameMatches = modsInTimetable.stream()
                .filter(m -> m.getName().equalsIgnoreCase(modQuery))
                .toList();

        if (nameMatches.size() > 1) {
            System.out.println("Multiple modules found with that name in your timetable. " +
                    "Please specify by module code:");
            nameMatches.forEach(m -> System.out.println("- " + m.getCode() + ": " + m.getName()));
            return;
        }

        if (nameMatches.size() == 1) {
            currentUser.removeModFromTimetable(timetableName, nameMatches.get(0));
        } else {
            System.out.println("Mod \"" + modQuery + "\" not found in timetable \"" + timetableName + "\".");
        }
    }
}

