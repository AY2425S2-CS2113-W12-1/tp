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
     * Attempts to retrieve detailed module information from a given module code or name.
     * <p>
     * This method searches through all available academic years in the
     * {@code allModCodesAndNamesByYear} map. It first tries to match the input as a
     * module code (case-insensitive), and if not found, it attempts to match by the
     * module name (also case-insensitive).
     * </p>
     * <p>
     * If a match is found, it calls {@code NUSModsAPI.fetchModuleByCode()} to retrieve
     * the full {@code Mod} object with detailed information.
     * </p>
     *
     * @param modCodeOrNameGiven the module code or name entered by the user
     * @return an {@code Optional<Mod>} containing the module details if found, or
     *         {@code Optional.empty()} if no matching module was found
     */

    public static Optional<Mod> modFromCodeOrName(String modCodeOrNameGiven) {
        String key = modCodeOrNameGiven.toUpperCase();
        Optional<CondensedMod> foundMod = Optional.empty();

        // check for a match with the module code across all years
        for (Map<String, CondensedMod> yearMap : allModCodesAndNamesByYear.values()) {
            if (yearMap.containsKey(key)) {
                foundMod = Optional.of(yearMap.get(key));
                break;
            }
        }

        if (foundMod.isEmpty()) {
            outer:
            for (Map<String, CondensedMod> yearMap : allModCodesAndNamesByYear.values()) {
                for (CondensedMod m : yearMap.values()) {
                    if (m.getName().equalsIgnoreCase(modCodeOrNameGiven)) {
                        foundMod = Optional.of(m);
                        break outer;
                    }
                }
            }
        }

        return foundMod.flatMap(mod -> NUSModsAPI.fetchModuleByCode(mod.getCode()));
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
