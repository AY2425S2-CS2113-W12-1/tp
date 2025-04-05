package modmate.command;

import modmate.download.nusmods.NUSModsAPI;
import modmate.download.nusmods.NUSModsUtil;
import modmate.log.LogUtil;
import modmate.mod.CondensedMod;
import modmate.user.User;
import modmate.CommandCenter;

import java.util.Map;

public class ViewAllModsCommand implements Command {

    public static final String CLI_REPRESENTATION = "viewallmods";

    private static final LogUtil logUtil = new LogUtil(ViewAllModsCommand.class);

    @Override
    public void execute(String[] args, User currentUser) {
        logUtil.info("Viewing all mods.");

        if (args.length == 2) {
            // viewallmods {year}
            String year = args[1];

            // Check if the year data is already loaded
            if (!CommandCenter.allModCodesAndNamesByYear.containsKey(year)) {
                // If not, fetch it and add to the map
                Map<String, CondensedMod> modMap = NUSModsAPI.fetchAllModCodes(Integer.parseInt(year));
                CommandCenter.allModCodesAndNamesByYear.put(year, modMap);
            }

            // Now retrieve and display the modules for that year
            Map<String, CondensedMod> modMap = CommandCenter.allModCodesAndNamesByYear.get(year);

            if (modMap == null || modMap.isEmpty()) {
                System.out.println("No module data found for academic year " + year);
                return;
            }

            System.out.println("Modules for academic year " + year + ":");
            modMap.entrySet()
                    .stream()
                    .sorted(Map.Entry.comparingByKey())
                    .forEach(entry ->
                            System.out.println(entry.getKey() + ": " + entry.getValue().getName())
                    );

        } else {
            // viewallmods → show the current year's modules
            String currentYear = String.valueOf(NUSModsUtil.getAdjustedYear());

            // Check if the current year's data is already loaded
            if (!CommandCenter.allModCodesAndNamesByYear.containsKey(currentYear)) {
                // If not, fetch it and add to the map
                Map<String, CondensedMod> currentYearMods = NUSModsAPI.fetchAllModCodes();
                CommandCenter.allModCodesAndNamesByYear.put(currentYear, currentYearMods);
                System.out.println("Module data for current academic year (" + currentYear + ") fetched and loaded.");
            }

            // Retrieve and display the modules for the current year
            Map<String, CondensedMod> modMap = CommandCenter.allModCodesAndNamesByYear.get(currentYear);

            if (modMap == null || modMap.isEmpty()) {
                System.out.println("No module data found for the current academic year.");
                return;
            }

            System.out.println("Modules for current academic year " + currentYear + ":");
            modMap.entrySet()
                    .stream()
                    .sorted(Map.Entry.comparingByKey())
                    .forEach(entry ->
                            System.out.println(entry.getKey() + ": " + entry.getValue().getName())
                    );
        }
    }
}
