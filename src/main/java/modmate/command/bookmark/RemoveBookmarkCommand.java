package modmate.command.bookmark;

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

import java.util.List;

public class RemoveBookmarkCommand extends Command {

    public static final String CLI_REPRESENTATION = "removebookmark";

    private static final LogUtil logUtil = new LogUtil(RemoveBookmarkCommand.class);

    private final Argument<String> modIdentifierArg;

    public RemoveBookmarkCommand(Input input) {
        super(input);
        this.modIdentifierArg = new Argument<>(
                "mod code or name",
                input.getArgument(),
                "The code or name of the mod to remove bookmark.",
                true
        );

        if (input.getArgument().isEmpty()) {
            throw new CommandException(this, "Mod code or name cannot be empty");
        }
    }

    @Override
    public String getSyntax() {
        return CommandUtil.buildSyntax(CLI_REPRESENTATION, List.of(modIdentifierArg));
    }

    @Override
    public String getDescription() {
        return "Remove bookmark of a mod.";
    }

    @Override
    public String getUsage() {
        return super.getUsage(List.of(modIdentifierArg));
    }

    @Override
    public void execute(User currentUser) {
        modIdentifierArg.getValue()
            .ifPresent(identifier -> {
                try {
                    Mod mod = NUSModsAPI.modFromIdentifier(identifier);
                    if (!currentUser.hasBookmark(mod)) {
                        logUtil.warning("Mod '" + mod.getCode() + "' not in bookmarks.");
                        System.out.println("Mod " + mod.getCode() + " isn't in your bookmarks.");
                        return;
                    }
                    currentUser.removeBookmark(mod);
                    logUtil.info("Removed mod '" + mod.getCode() + "' from bookmarks.");
                    System.out.println("Removed mod " + mod.getCode() + " from bookmarks.");
                } catch (ApiException e) {
                    System.out.println(e.getMessage());
                }
            });
    }
}
