package modmate.download.json;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Collections;

import modmate.mod.attribute.classslot.ClassSlot;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import modmate.lesson.EventFactory;
import modmate.lesson.Period;
import modmate.lesson.types.Lesson;
import modmate.mod.Mod;
import modmate.mod.attribute.Faculty;
import modmate.mod.attribute.ModAttributes;
import modmate.mod.attribute.Semester;
import modmate.mod.attribute.WeeklyWorkload;

/**
 * ModJSONParser is responsible for reading and parsing JSON data to create Mod
 * objects.
 */
public class ModJSONParser {

    private final ModJSONUtil jsonUtil;

    /**
     * Constructs a ModJSONParser with the given JSONObject.
     *
     * @param jsonObject the JSONObject to read from
     */
    public ModJSONParser(JSONObject jsonObject) {
        this.jsonUtil = new ModJSONUtil(jsonObject);
    }

    /**
     * Retrieves a Mod object from the JSON data.
     *
     * @return a Mod object
     * @throws JSONException if there is an error parsing the JSON data
     */
    public Mod getModule() throws JSONException {
        return new Mod(
                getName(),
                getCode(),
                getDescription(),
                getModAttributes());
    }

    /**
     * Retrieves the name of the module from the JSON data.
     *
     * @return the name of the module
     * @throws JSONException if there is an error parsing the JSON data
     */
    public String getName() throws JSONException {
        return this.jsonUtil.getString(ModJSONKey.NAME);
    }

    /**
     * Retrieves the code of the module from the JSON data.
     *
     * @return the code of the module
     * @throws JSONException if there is an error parsing the JSON data
     */
    public String getCode() throws JSONException {
        return this.jsonUtil.getString(ModJSONKey.CODE);
    }

    /**
     * Retrieves the description of the module from the JSON data.
     *
     * @return the description of the module
     * @throws JSONException if there is an error parsing the JSON data
     */
    private String getDescription() throws JSONException {
        return this.jsonUtil.getString(ModJSONKey.DESCRIPTION);
    }

    /**
     * Retrieves the attributes of the module from the JSON data.
     *
     * @return the attributes of the module
     * @throws JSONException if there is an error parsing the JSON data
     */
    private ModAttributes getModAttributes() throws JSONException {
        return new ModAttributes(
                getFaculty(),
                getAvailableSemesters(),
                getUnits(),
                getIsGraded(),
                getPrerequisites(),
                getWorkload(),
                getClassSlots());
    }

    /**
     * Retrieves the class slots of the module from the Events in the JSON data.
     *
     * @return a list of class slots
     */
    private List<ClassSlot> getClassSlots() {
        List<Lesson> events = getAllModEvents();
        Map<String, List<Lesson>> eventMap = new HashMap<>();

        for (Lesson event : events) {
            eventMap.computeIfAbsent(event.getId(), k -> new ArrayList<>()).add(event);
        }

        List<ClassSlot> classSlots = new ArrayList<>();
        for (Map.Entry<String, List<Lesson>> entry : eventMap.entrySet()) {
            classSlots.add(new ClassSlot(entry.getValue(), entry.getKey()));
        }

        classSlots.sort((slot1, slot2) -> {
            // This is just to compare class numbers in the format of "1A", "1B", "1C", etc.
            // Its unnecessary but it was annoying me
            String classNo1 = slot1.getClassNo();
            String classNo2 = slot2.getClassNo();

            String[] parts1 = classNo1.split("(?<=\\d)(?=\\D)");
            String[] parts2 = classNo2.split("(?<=\\d)(?=\\D)");

            int numComparison = Integer.compare(Integer.parseInt(parts1[0]), Integer.parseInt(parts2[0]));
            if (numComparison != 0) {
                return numComparison;
            }

            if (parts1.length > 1 && parts2.length > 1) {
                return parts1[1].compareTo(parts2[1]);
            } else if (parts1.length > 1) {
                return 1;
            } else if (parts2.length > 1) {
                return -1;
            }

            return 0;
        });

        return classSlots;
    }



    /**
     * Retrieves all module events from the JSON data.
     *
     * @return a list of events
     */
    private List<Lesson> getAllModEvents() {
        JSONObject jsonObject = jsonUtil.getJSONObject();
        // I know, I'm sorry, I'm too tired to figure out how the abstract version works sorry
        List<Lesson> events = new ArrayList<>();

        JSONArray semesterDataArray = jsonObject.getJSONArray("semesterData");
        for (int i = 0; i < semesterDataArray.length(); i++) {
            // For each semester
            JSONArray timetableArray = semesterDataArray.getJSONObject(i).getJSONArray("timetable");

            for (int j = 0; j < timetableArray.length(); j++) {
                // For each event in the timetable
                JSONObject eventObj = timetableArray.getJSONObject(j);

                // Extract the weeks array
                JSONArray weeksArray = eventObj.getJSONArray("weeks");
                List<Integer> weeks = new ArrayList<>();
                for (int k = 0; k < weeksArray.length(); k++) {
                    weeks.add(weeksArray.getInt(k));
                }

                HashMap<Integer, Boolean> occurrenceByWeek = new HashMap<>();

                for (int week : weeks) {
                    occurrenceByWeek.put(week, true);
                }

                Period period = new Period(
                        DayOfWeek.valueOf(eventObj.getString("day").toUpperCase()),
                        LocalTime.parse(eventObj.getString("startTime"), DateTimeFormatter.ofPattern("HHmm")),
                        LocalTime.parse(eventObj.getString("endTime"), DateTimeFormatter.ofPattern("HHmm")),
                        occurrenceByWeek
                );

                // Create a new Event of the correct type for it
                events.add(EventFactory.createEvent(
                        eventObj.getString("lessonType"),
                        period,
                        eventObj.getString("venue"),
                        eventObj.getString("classNo")
                ));
            }
        }
        return events;
    }

    /**
     * Retrieves the faculty of the module from the JSON data.
     *
     * @return the faculty of the module
     * @throws JSONException if there is an error parsing the JSON data
     */
    private Faculty getFaculty() throws JSONException {
        return new Faculty(this.jsonUtil.getString(ModJSONKey.FACULTY));
    }

    /**
     * Retrieves the available semesters of the module from the JSON data.
     *
     * @return a list of available semesters
     * @throws JSONException if there is an error parsing the JSON data
     */
    private List<Semester> getAvailableSemesters() throws JSONException {
        return this.jsonUtil.getJSONArray(ModJSONKey.AVAILABLE_SEMESTERS)
                .toList()
                .stream()
                .filter(obj -> obj instanceof Map) // Ensure obj is a Map
                .map(obj -> (Map<?, ?>) obj) // Cast obj to Map
                .map(map -> {
                    Object semesterObj = map.get("semester");

                    // Map the semester number to the corresponding enum
                    if (semesterObj instanceof Integer) {
                        return Semester.fromInt((Integer) semesterObj);
                    }

                    throw new IllegalArgumentException(
                            "Invalid semester format: " + semesterObj);
                })
                .toList();
    }

    /**
     * Retrieves the number of units of the module from the JSON data.
     *
     * @return the number of units
     * @throws JSONException if there is an error parsing the JSON data
     */
    private int getUnits() throws JSONException {
        return this.jsonUtil.getInt(ModJSONKey.UNITS);
    }

    /**
     * Retrieves the prerequisites of the module from the JSON data.
     *
     * @return a list of prerequisite modules
     * @throws JSONException if there is an error parsing the JSON data
     */
    private List<Mod> getPrerequisites() throws JSONException {
        return Collections.emptyList();
    }

    /**
     * Checks if the module is graded from the JSON data.
     *
     * @return true if the module is graded, false otherwise
     * @throws JSONException if there is an error parsing the JSON data
     */
    private boolean getIsGraded() throws JSONException {
        return this.jsonUtil.getString(ModJSONKey.IS_GRADED).equals("Graded");
    }

    /**
     * Retrieves the weekly workload of the module from the JSON data.
     *
     * @return the weekly workload
     * @throws JSONException if there is an error parsing the JSON data
     */
    private WeeklyWorkload getWorkload() throws JSONException {
        JSONArray workloadJSONArray = this.jsonUtil.getJSONArray(ModJSONKey.WORKLOAD);

        return new WeeklyWorkload(
                workloadJSONArray.getDouble(0),
                workloadJSONArray.getDouble(1),
                workloadJSONArray.getDouble(3),
                workloadJSONArray.getDouble(4));
    }

}
