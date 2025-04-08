package modmate.command.mod;

import java.util.List;

import modmate.command.Command;
import modmate.command.CommandUtil;
import modmate.command.util.Argument;
import modmate.download.nusmods.NUSModsAPI;
import modmate.exception.ApiException;
import modmate.exception.CommandException;
import modmate.log.LogUtil;
import modmate.mod.Mod;
import modmate.ui.Input;
import modmate.user.User;

public class ViewModCommand extends Command {

    public static final String CLI_REPRESENTATION = "viewmod";

    private static final LogUtil logUtil = new LogUtil(ViewModCommand.class);

    private final Argument<String> modIdentifierArg;

    public ViewModCommand(Input input) {
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
    public String getSyntax() {
        return CommandUtil.buildSyntax(CLI_REPRESENTATION, List.of(modIdentifierArg));
    }

    @Override
    public String getDescription() {
        return "View details of a mod by its mod code or name.";
    }

    @Override
    public String getUsage() {
        return super.getUsage(List.of(modIdentifierArg));
    }

    @Override
    public void execute(User user) throws ApiException {
        logUtil.info("Executing view mod command.");

        modIdentifierArg.getValue().ifPresentOrElse(
            identifier -> {
                logUtil.info("Viewing mod details for: " + identifier);
                try {
                    Mod mod = NUSModsAPI.modFromIdentifier(identifier);
                    System.out.println(mod.getDetailedString());
                    logUtil.info("Mod details displayed.");
                } catch (ApiException e) {
                    logUtil.severe("Failed to fetch mod details: " + e.getMessage());
                    System.out.println(e.getMessage());
                }
            },
            () -> {
                String message = "Mod code or name cannot be empty.";
                logUtil.severe(message);
                throw new CommandException(this, message);
            });
    }

}
