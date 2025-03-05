package modmate.user;

import modmate.course.Course;

import java.util.ArrayList;
import java.util.List;

public class User {
    private List<Course> bookmarks;

    // List to store the user's timetables
    private List<Timetable> timetables;

    // Constructor to initialize the user's courses and timetables
    public User() {
        this.timetables = new ArrayList<>();
        this.bookmarks = new ArrayList<>();
    }

    // Method to add a new timetable for the user
    public void addTimetable(String timetableName) {
        System.out.println("Noone has implemented me yet!! AAARGH!!");
        // TODO add a new timetable with name to timetables
    }

    // Method to display the user's courses and timetables
    public String getTimetable(String timetableName) {
        return("Noone has implemented me yet!! AAARGH!!");
        // TODO print timetable if it exists, otherwise just print "nooooo" or something
    }

    // TODO getters and setters for bookmarks, timetables, and
}
