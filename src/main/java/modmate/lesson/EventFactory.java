package modmate.lesson;

import java.util.Map;

import modmate.lesson.types.Lecture;
import modmate.lesson.types.Lesson;
import modmate.lesson.types.Tutorial;

public class EventFactory {
    private static final Map<String, TriFunction<Period, String, String, Lesson>> EVENT_MAP = Map.of(
            "Lecture", Lecture::new,
            "Tutorial", Tutorial::new
    );

    public static Lesson createEvent(String eventType, Period period, String location, String classNo) {
        return EVENT_MAP.getOrDefault(eventType, DefaultEvent::new).apply(period, location, classNo);
    }
}
