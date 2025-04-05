package modmate.command;

import modmate.CommandCenter;
import modmate.timetable.BreakPeriod;
import modmate.timetable.Lesson;
import modmate.timetable.Period;
import modmate.user.Schedule;
import modmate.log.LogUtil;
import modmate.user.User;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

public class ViewTimetableCommand implements Command {

    public static final String CLI_REPRESENTATION = "timetable";

    private static final LogUtil logUtil = new LogUtil(ViewTimetableCommand.class);

    private static class TimelineEntry {
        String label;
        Period period;
        boolean isBreak;

        TimelineEntry(String label, Period period, boolean isBreak) {
            this.label = label;
            this.period = period;
            this.isBreak = isBreak;
        }
    }

    @Override
    public void execute(String[] args, User currentUser) {
        if (args.length < 2) {
            System.out.println("Usage: timetable <timetable>");
            return;
        }


        String viewtype = "default";
        String inputTimetableName;

        if (args.length >= 3 && args[1].equals("timeline")) {
            viewtype = "timeline";
            inputTimetableName = CommandCenter.stringFromBetweenPartsXY(args, 2);
        } else {
            inputTimetableName = CommandCenter.stringFromBetweenPartsXY(args, 1);
        }

        assert inputTimetableName != null
                && !inputTimetableName.trim().isEmpty() : "Timetable name cannot be null or empty";

        Schedule timetable = currentUser.getTimetable(inputTimetableName);
        if (!currentUser.hasTimetable(inputTimetableName) || timetable == null) {
            logUtil.warning("Timetable '" + inputTimetableName + "' not found.");
            return;
        }

        logUtil.info("Displaying user's mod list.");
        if (viewtype.equals("timeline")) {
            HashMap<DayOfWeek, List<TimelineEntry>> timeline = new HashMap<>();
            timetable.getMods().forEach(
                    mod -> {
                        for (String type: timetable.getModSchedule(mod).getLessonTypes()) {
                            for (Lesson lesson: timetable.getModSchedule(mod).getSelectedLessons(type)){
                                DayOfWeek day = lesson.getPeriod().getDay();
                                if (!timeline.containsKey(day)) {
                                    timeline.put(day, new ArrayList<>());
                                }
                                timeline.get(day).add(
                                        new TimelineEntry(mod.getCode(), lesson.getPeriod(), false)
                                );
                            }
                        }

                    }
            );
            for (BreakPeriod breakPeriod : timetable.getBreaks()) {
                DayOfWeek day = breakPeriod.getPeriod().getDay();
                if (!timeline.containsKey(day)) {
                    timeline.put(day, new ArrayList<>());
                }
                timeline.get(day).add(new TimelineEntry(
                        "Break: " + breakPeriod.getLabel(),
                        breakPeriod.getPeriod(),
                        true));
            }



            for (DayOfWeek day : DayOfWeek.values()) {
                if (day == DayOfWeek.SATURDAY || day == DayOfWeek.SUNDAY) {
                    continue;
                }
                System.out.println("[" + day + "]");
                List<TimelineEntry> entries = timeline.getOrDefault(day, new ArrayList<>())
                        .stream()
                        .sorted(Comparator.comparing(e -> e.period.getStartTime()))
                        .toList();

                for (TimelineEntry entry : entries) {
                    String start = entry.period.getStartTime().toString();
                    String end = entry.period.getEndTime().toString();
                    System.out.println(" | " + entry.label + ": " + start + "-" + end);
                }

            }
        } else {
            System.out.println(timetable.toString());
        }
        logUtil.info("Displaying user's mod list.");
    }

}
