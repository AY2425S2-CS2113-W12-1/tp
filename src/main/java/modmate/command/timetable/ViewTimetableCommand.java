package modmate.command.timetable;

import modmate.command.Command;
import modmate.command.CommandUtil;
import modmate.command.util.Argument;
import modmate.exception.CommandException;
import modmate.timetable.BreakPeriod;
import modmate.timetable.Lesson;
import modmate.timetable.Period;
import modmate.ui.Input;
import modmate.user.Schedule;
import modmate.log.LogUtil;
import modmate.user.User;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

public class ViewTimetableCommand extends Command {

    public static final String CLI_REPRESENTATION = "timetable";

    private static final LogUtil logUtil = new LogUtil(ViewTimetableCommand.class);

    private final Argument<String> timetableNameArg;
    private final Argument<String> timetableTypeArg;

    private static class TimelineEntry {
        String label;
        Period period;
        boolean isBreak;
        boolean isClashing;

        TimelineEntry(String label, Period period, boolean isBreak, boolean isClashing) {
            this.label = label;
            this.period = period;
            this.isBreak = isBreak;
            this.isClashing = isClashing;
        }

        TimelineEntry(String label, Period period, boolean isBreak) {
            this(label, period, isBreak, false);
        }

        public void setClashing(boolean isClashing) {
            this.isClashing = isClashing;
        }


    }

    public ViewTimetableCommand(Input input) {
        super(input);
        String argument = input.getArgument();
        String type;
        String name;
        if (argument.startsWith("timeline ") || argument.startsWith("list ")) {
            name = input.getArgument().replaceFirst("^\\S+\\s?", "");
            type = input.getArgument().split(" ")[0];
        } else {
            name = input.getArgument();
            type = "timeline";
        }
        this.timetableNameArg = new Argument<>(
                "timetable name",
                name,
                "The name of the timetable to display",
                true
        );
        this.timetableTypeArg = new Argument<>(
                "timetable type",
                type,
                "The type of the timetable to display",
                false
        );

        if (timetableNameArg.getValue().isEmpty()) {
            throw new CommandException(this, "Timetable name cannot be empty");
        }
    }

    @Override
    public String getSyntax() {
        return CommandUtil.buildSyntax(
                CLI_REPRESENTATION,
                List.of(timetableTypeArg, timetableNameArg)
        );
    }

    @Override
    public String getDescription() {
        return "Display your mod timetable.";
    }

    @Override
    public String getUsage() {
        return super.getUsage()
                + "  [timeline]: Optional. Display timetable as a timeline.\n"
                + "  <timetable>: The name of the timetable to display.";
    }

    @Override
    public void execute(User currentUser) {
        logUtil.info("Displaying user's mod list.");

        String timetableType = timetableTypeArg.getValue().orElse("timeline");
        String timetableName = timetableNameArg.getValue().orElse(null);

        assert timetableName != null
                && !timetableName.trim().isEmpty() : "Timetable name cannot be null or empty";

        Schedule timetable = currentUser.getTimetable(timetableName);
        if (!currentUser.hasTimetable(timetableName) || timetable == null) {
            logUtil.warning("Timetable '" + timetableName + "' not found.");
            return;
        }

        logUtil.info("Displaying user's mod list.");
        if (timetableType.equals("timeline")) {
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
                                        new TimelineEntry(
                                                mod.getCode() +
                                                        " " +
                                                        lesson.getType(),
                                                lesson.getPeriod(),
                                                false)
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

                entries.forEach(entry -> {
                    boolean isClashing = entries.stream().anyMatch(
                            (otherEntry) ->
                                    !otherEntry.label.equals(entry.label) &&
                                            otherEntry.period.isClashing(entry.period));
                    entry.setClashing(isClashing);
                });

                for (TimelineEntry entry : entries) {
                    String start = entry.period.getStartTime().toString();
                    String end = entry.period.getEndTime().toString();
                    String line = entry.isClashing ? " ╳ " : " │ ";
                    System.out.println(line + entry.label + ": " + start + "-" + end);
                }

            }
        } else {
            System.out.println(timetable);
        }
    }

}
