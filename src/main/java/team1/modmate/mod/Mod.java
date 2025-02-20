package team1.modmate.mod;

import team1.modmate.mod.events.Event;
import team1.modmate.mod.modattributes.Course;
import team1.modmate.mod.modattributes.School;

import java.util.List;

public class Mod {
    private String code;
    private String name;
    private Course course;
    private School school;
    private int units;
    private SemesterAvailability semesterAvailability;
    private boolean isGraded;
    private String description;
    private List<String> prerequisites;
    private double lectureHours;
    private double tutorialHours;
    private double projectHours;
    private double preparationHours;
    private List<Event> timetable;

    public enum SemesterAvailability {
        SEM1, SEM2, BOTH
    }

    public Mod(String code, String name, Course course, School school, int units,
               SemesterAvailability semesterAvailability, boolean isGraded, String description,
               List<String> prerequisites, double lectureHours, double tutorialHours,
               double projectHours, double preparationHours, List<Event> timetable) {
        this.code = code;
        this.name = name;
        this.course = course;
        this.school = school;
        this.units = units;
        this.semesterAvailability = semesterAvailability;
        this.isGraded = isGraded;
        this.description = description;
        this.prerequisites = prerequisites;
        this.lectureHours = lectureHours;
        this.tutorialHours = tutorialHours;
        this.projectHours = projectHours;
        this.preparationHours = preparationHours;
        this.timetable = timetable;
    }

    public double getTotalWorkload() {
        return lectureHours + tutorialHours + projectHours + preparationHours;
    }

    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public Course getCourse() {
        return course;
    }
    public void setCourse(Course course) {
        this.course = course;
    }

    public School getSchool() {
        return school;
    }
    public void setSchool(School school) {
        this.school = school;
    }

    public int getUnits() {
        return units;
    }
    public void setUnits(int units) {
        this.units = units;
    }

    public SemesterAvailability getSemesterAvailability() {
        return semesterAvailability;
    }
    public void setSemesterAvailability(SemesterAvailability semesterAvailability) {
        this.semesterAvailability = semesterAvailability;
    }

    public boolean isGraded() {
        return isGraded;
    }
    public void setGraded(boolean graded) {
        isGraded = graded;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getPrerequisites() {
        return prerequisites;
    }
    public void setPrerequisites(List<String> prerequisites) {
        this.prerequisites = prerequisites;
    }

    public double getLectureHours() {
        return lectureHours;
    }
    public void setLectureHours(double lectureHours) {
        this.lectureHours = lectureHours;
    }

    public double getTutorialHours() {
        return tutorialHours;
    }
    public void setTutorialHours(double tutorialHours) {
        this.tutorialHours = tutorialHours;
    }

    public double getProjectHours() { return projectHours; }
    public void setProjectHours(double projectHours) {
        this.projectHours = projectHours;
    }

    public double getPreparationHours() { return preparationHours; }
    public void setPreparationHours(double preparationHours) {
        this.preparationHours = preparationHours;
    }

    public List<Event> getTimetable() {
        return timetable;
    }
    public void setTimetable(List<Event> timetable) {
        this.timetable = timetable;
    }
    @Override
    public String toString() {
        return code + " " + name;
    }
}
