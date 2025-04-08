package modmate.command.mod;

import java.util.List;
import java.util.Map;

import modmate.command.Command;
import modmate.command.CommandUtil;
import modmate.download.nusmods.NUSModsAPI;
import modmate.log.LogUtil;
import modmate.ui.Input;
import modmate.ui.Pagination;
import modmate.user.User;

public class ViewAllModsCommand extends Command {

    public static final String CLI_REPRESENTATION = "viewallmods";

    private static final LogUtil logUtil = new LogUtil(ViewAllModsCommand.class);

    public ViewAllModsCommand(Input input) {
        super(input);
    }

    @Override
    public String getSyntax() {
        return CommandUtil.buildSyntax(CLI_REPRESENTATION);
    }

    @Override
    public String getDescription() {
        return "View all available mods.";
    }

    @Override
    public void execute(User user) {
        logUtil.info("Viewing all mods.");

        List<String> modList = NUSModsAPI.CONDENSED_MODS
            .entrySet()
            .stream()
            .sorted(Map.Entry.comparingByKey())
            .map(entry -> entry.getKey() + ": " + entry.getValue().getName())
            .toList();

        if (modList.isEmpty()) {
            System.out.println("No mods available.");
            return;
        }

        Pagination<String> pagination = new Pagination<>(modList, 15);
        pagination.display();
    }

}
