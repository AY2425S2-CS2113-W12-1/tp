package modmate.command;

import modmate.CommandCenter;
import modmate.download.nusmods.NUSModsAPI;
import modmate.log.LogUtil;
import modmate.mod.Mod;
import modmate.user.User;

import java.util.List;
import java.util.Optional;

public class SearchModCommand implements Command {

    public static final String CLI_REPRESENTATION = "searchmod";

    private static final LogUtil logUtil = new LogUtil(SearchModCommand.class);

    @Override
    public void execute(String[] args, User currentUser) {
        if (args.length < 2) {
            System.out.println("Usage: searchmod <search query>");
            return;
        }

        String inputSearchQuery = args[1].trim();

        assert inputSearchQuery != null && !inputSearchQuery.isEmpty() : "Search query cannot be null or empty";

        logUtil.info("User is searching for a mod.");
        List<Mod> searchResults = getSearchResults(inputSearchQuery);

        if (!searchResults.isEmpty()) {
            for (Mod mod : searchResults) {
                System.out.println(mod);
            }
        } else {
            System.out.println("No mods found matching the search query.");
        }

    }

    private static List<Mod> getSearchResults(String searchTerm) {
        logUtil.info("Internally invoking search for " + searchTerm + ".");
        return CommandCenter.allModCodesAndNamesByYear.values().stream()
                .flatMap(map -> map.values().stream())
                .filter(condensedMod ->
                        condensedMod.getName().toLowerCase().contains(searchTerm.toLowerCase()) ||
                                condensedMod.getCode().toLowerCase().contains(searchTerm.toLowerCase()))
                .map(condensedMod -> NUSModsAPI.fetchModuleByCode(condensedMod.getCode()))
                .flatMap(Optional::stream)
                .toList();
    }

}
