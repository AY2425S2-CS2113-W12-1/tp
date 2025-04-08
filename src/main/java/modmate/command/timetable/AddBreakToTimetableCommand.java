package modmate.command.timetable;

import modmate.command.Command;
import modmate.exception.ApiException;
import modmate.exception.UserException;
import modmate.exception.CommandException;
import modmate.timetable.BreakPeriod;
import modmate.timetable.Period;
import modmate.user.User;
import modmate.ui.Input;


import java.time.DayOfWeek;
import java.time.LocalTime;

public class AddBreakToTimetableCommand extends Command {

    public static final String CLI_REPRESENTATION = "addbreak";

    public AddBreakToTimetableCommand(Input input) {
        super(input);
    }

    @Override
    public String getDescription() {
        return "Add a break to your timetable.";
    }

    @Override
    public String getSyntax() {
        return CLI_REPRESENTATION + " <timetable> <label> <day> <startTime> <endTime>";
    }

    @Override
    public String getUsage() {
        return super.getUsage()
                + "  <timetable>: The name of the timetable.\n"
                + "  <label>: A label for the break.\n"
                + "  <day>: The day of the week (e.g., MONDAY).\n"
                + "  <startTime>: The start time in HH:mm format.\n"
                + "  <endTime>: The end time in HH:mm format.";
    }

    @Override
    public void execute(User currentUser)
            throws CommandException, UserException, ApiException {
        String[] args = input.getArgument().split(" ", 5);
        if (args.length < 5) {
            System.out.println("Usage: " + getSyntax());
            return;
        }

        String timetableName = args[0];
        String label = args[1];
        String dayInput = args[2];
        String startTimeInput = args[3];
        String endTimeInput = args[4];

        assert timetableName != null && !timetableName.trim().isEmpty() : "Timetable name cannot be null or empty";
        assert label != null && !label.trim().isEmpty() : "Label cannot be null or empty";

        DayOfWeek day;
        LocalTime start;
        LocalTime end;

        try {
            day = DayOfWeek.valueOf(dayInput.toUpperCase());

            String startTime = formatTime(startTimeInput);
            String endTime = formatTime(endTimeInput);

            start = LocalTime.parse(startTime);
            end = LocalTime.parse(endTime);

            if (!end.isAfter(start)) {
                System.out.println("End time must be after start time.");
                return;
            }

            assert start != null && end != null : "Start or end time cannot be null";
            assert !end.isBefore(start) : "End time must be after start time";
            assert start.getHour() < 24 && end.getHour() < 24 : "Time input must be within 00:00 to 23:59";

        } catch (Exception e) {
            System.out.println("Invalid input format. Use correct 24-hour format: MONDAY 12:00 13:00");
            return;
        }

        BreakPeriod breakPeriod = new BreakPeriod(label, new Period(day, start, end));
        boolean success = currentUser.addBreakToTimetable(timetableName, breakPeriod);

        if (success) {
            System.out.println("Break [" + label + "] added to timetable '" + timetableName + "'.");
        } else {
            System.out.println("Timetable '" + timetableName + "' not found.");
        }
    }

    private String formatTime(String timeInput) {
        if (timeInput == null || timeInput.trim().isEmpty()) {
            throw new IllegalArgumentException("Time input cannot be null or empty");
        }

        if (timeInput.length() == 4 && timeInput.matches("\\d{4}")) {
            return timeInput.substring(0, 2) + ":" + timeInput.substring(2);
        }

        return timeInput;
    }
}
