package modmate.command.bookmark;

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

public class BookmarkCommand extends Command {

    public static final String CLI_REPRESENTATION = "bookmark";

    private static final LogUtil logUtil = new LogUtil(BookmarkCommand.class);

    private final Argument<String> modIdentifierArg;

    public BookmarkCommand(Input input) {
        super(input);
        this.modIdentifierArg = new Argument<>(
            "mod code or name",
            input.getArgument(),
            "The code or name of the mod to bookmark.",
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
        return "Bookmark a mod for later reference.";
    }

    @Override
    public String getUsage() {
        return super.getUsage(List.of(modIdentifierArg));
    }

    @Override
    public void execute(User currentUser) throws ApiException {
        logUtil.info("Bookmarking mod.");

        modIdentifierArg.getValue()
            .ifPresent(identifier -> {
                try {
                    Mod mod = NUSModsAPI.modFromIdentifier(identifier);

                    // Bookmark a mod for later reference
                    currentUser.addBookmark(mod);

                    logUtil.info("Mod '" + identifier + "' bookmarked.");
                    System.out.println("Bookmark " + identifier + " successfully added to your list.");
                } catch (ApiException e) {
                    System.out.println(e.getMessage());
                }
            });
    }

}
