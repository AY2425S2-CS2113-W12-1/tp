package modmate;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import modmate.command.CommandLine;
import modmate.download.nusmods.NUSModsAPI;
import modmate.download.nusmods.NUSModsUtil;
import modmate.log.LogUtil;
import modmate.mod.CondensedMod;
import modmate.mod.Mod;
import modmate.user.User;

public class CommandCenter {

    static Map<String, CondensedMod> currentYearMods = NUSModsAPI.fetchAllModCodes();

    public static Map<String, Map<String, CondensedMod>> allModCodesAndNamesByYear = new HashMap<>() {{
        put(String.valueOf(NUSModsUtil.getAdjustedYear()), currentYearMods);
    }};

    private static final LogUtil logUtil = new LogUtil(CommandCenter.class);

    public static void handleCommand(String commandName, String[] args, User currentUser) {
        CommandLine.getCommand(commandName)
            .ifPresentOrElse(
                command -> command.execute(args, currentUser),
                () -> System.out.println("Invalid command: " + commandName)
            );
    }

    /**
     * Helper method that searches for an exact matching mod by its code or name.
     *
     * @param modCodeOrNameGiven The code or name of the mod to search for.
     * @return The mod that matches the given code or name, or null if no match is
     *         found.
     */
    public static Optional<Mod> modFromCodeOrName(String modCodeOrNameGiven) {
        // First, check for a match with the module code (key)
        Optional<CondensedMod> condensedMod = Optional.ofNullable(
                allModCodesAndNames.get(modCodeOrNameGiven.toUpperCase())
        );

        // If a match is found, retrieve mod details using the module code
        return condensedMod.flatMap(mod -> NUSModsAPI.fetchModuleByCode(mod.getCode()));
    }

    /**
     * Helper method to extract a substring from the command input.
     *
     * @param inputParts The split command input.
     * @param x          The starting index of the substring.
     * @return The concatenated string from index x to the end of the input.
     */
    public static String stringFromBetweenPartsXY(String[] inputParts, int x) {
        return stringFromBetweenPartsXY(inputParts, x, inputParts.length);
    }

    /**
     * Helper method to extract a substring from the command input between two
     * indices.
     *
     * @param inputParts The split command input.
     * @param x          The starting index of the substring.
     * @param y          The ending index of the substring.
     * @return The concatenated string between indices x and y in the input.
     */
    public static String stringFromBetweenPartsXY(String[] inputParts, int x, int y) {
        if (inputParts == null || inputParts.length == 0 || x < 0 || y > inputParts.length || x >= y) {
            return "";
        }

        StringBuilder sb = new StringBuilder();
        for (int i = x; i < y; i++) {
            sb.append(inputParts[i]);
            if (i < y - 1) {
                sb.append(" ");
            }
        }
        return sb.toString();
    }
}
