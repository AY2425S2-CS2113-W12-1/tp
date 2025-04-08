package modmate.ui;

import modmate.command.bookmark.BookmarkCommand;
import modmate.command.Command;
import modmate.command.timetable.CreateTimetableCommand;
import modmate.command.system.ExitCommand;
import modmate.command.bookmark.GetBookmarksCommand;
import modmate.command.system.HelpCommand;
import modmate.command.bookmark.RemoveBookmarkCommand;
import modmate.command.mod.SetModLessonCommand;
import modmate.command.mod.ViewAllModsCommand;
import modmate.command.mod.ViewModCommand;
import modmate.command.mod.ViewModLessonsCommand;
import modmate.command.timetable.ViewTimetableCommand;
import modmate.command.search.SearchModCommand;
import modmate.exception.CommandException;
import modmate.command.timetable.AddModToTimetableCommand;
import modmate.command.timetable.RemoveModFromTimetableCommand;
import modmate.command.timetable.AddBreakToTimetableCommand;

public class CommandParser {

    public static Command parse(Input input)
            throws CommandException, IllegalArgumentException {
        Command command;

        switch (input.getCommand()) {
        case HelpCommand.CLI_REPRESENTATION -> command = new HelpCommand(input);
        case AddBreakToTimetableCommand.CLI_REPRESENTATION -> command = new AddBreakToTimetableCommand(input);
        case AddModToTimetableCommand.CLI_REPRESENTATION -> command = new AddModToTimetableCommand(input);
        case BookmarkCommand.CLI_REPRESENTATION -> command = new BookmarkCommand(input);
        case GetBookmarksCommand.CLI_REPRESENTATION -> command = new GetBookmarksCommand(input);
        case SearchModCommand.CLI_REPRESENTATION -> command = new SearchModCommand(input);
        case ViewModCommand.CLI_REPRESENTATION -> command = new ViewModCommand(input);
        case RemoveBookmarkCommand.CLI_REPRESENTATION -> command = new RemoveBookmarkCommand(input);
        case SetModLessonCommand.CLI_REPRESENTATION -> command = new SetModLessonCommand(input);
        case ViewModLessonsCommand.CLI_REPRESENTATION -> command = new ViewModLessonsCommand(input);
        case RemoveModFromTimetableCommand.CLI_REPRESENTATION -> command = new RemoveModFromTimetableCommand(input);
        case CreateTimetableCommand.CLI_REPRESENTATION -> command = new CreateTimetableCommand(input);
        case ViewTimetableCommand.CLI_REPRESENTATION -> command = new ViewTimetableCommand(input);
        case ViewAllModsCommand.CLI_REPRESENTATION -> command = new ViewAllModsCommand(input);
        case ExitCommand.CLI_REPRESENTATION -> command = new ExitCommand(input);
        default -> throw new IllegalArgumentException("Input error: Invalid input");
        }

        return command;
    }

}
