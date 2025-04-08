package modmate.command.bookmark;

import java.util.List;

import modmate.command.Command;
import modmate.command.CommandUtil;
import modmate.log.LogUtil;
import modmate.mod.Mod;
import modmate.ui.Input;
import modmate.user.User;

public class GetBookmarksCommand extends Command {

    public static final String CLI_REPRESENTATION = "bookmarks";

    private static final LogUtil logUtil = new LogUtil(GetBookmarksCommand.class);

    public GetBookmarksCommand(Input input) {
        super(input);
    }

    @Override
    public String getSyntax() {
        return CommandUtil.buildSyntax(CLI_REPRESENTATION);
    }

    @Override
    public String getDescription() {
        return "View all bookmarked mods.";
    }

    @Override
    public void execute(User currentUser) {
        logUtil.info("Viewing bookmarks.");

        // View all bookmarked courses
        List<Mod> bookmarks = currentUser.getBookmarks();

        System.out.println("You have " + bookmarks.size() + " bookmark(s).");

        for (Mod mod : bookmarks) {
            System.out.println(mod);
        }
    }
}
