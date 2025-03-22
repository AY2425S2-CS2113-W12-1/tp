package modmate.mod.attribute.classslot;

import java.util.List;

import modmate.lesson.types.Lesson;

public class ClassSlot {
    private final String classNo;
    private final List<Lesson> events;

    public ClassSlot(List<Lesson> events, String classNo) {
        this.events = events;
        this.classNo = classNo;
    }

    public String getClassNo() {
        return classNo;
    }

    @Override
    public String toString() {
        return classNo;
    }
}
