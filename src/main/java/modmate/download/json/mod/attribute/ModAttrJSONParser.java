package modmate.download.json.mod.attribute;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import modmate.download.json.JSONParser;
import modmate.mod.Mod;
import modmate.mod.attribute.Faculty;
import modmate.mod.attribute.ModAttributes;
import modmate.mod.attribute.WeeklyWorkload;
import modmate.timetable.Semester;

public class ModAttrJSONParser extends JSONParser<ModAttrJSONKey> {

    private final List<Semester> availableSemesters;

    public ModAttrJSONParser(JSONObject jsonObject, List<Semester> semesters) {
        super(jsonObject);
        this.availableSemesters = semesters;
    }

    public ModAttributes getAttributes() {
        Faculty faculty = new Faculty(this.getString(ModAttrJSONKey.FACULTY));
        double units = this.getDouble(ModAttrJSONKey.UNITS);
        boolean isGraded = this.getString(ModAttrJSONKey.IS_GRADED).equals("Graded");
        Optional<WeeklyWorkload> workload = getWorkload();

        List<Mod> prerequisites = Collections.emptyList();

        return new ModAttributes(faculty, this.availableSemesters, units,
                isGraded, prerequisites, workload);
    }

    /**
     * Retrieves the weekly workload of the module from the JSON data.
     *
     * @return the weekly workload
     * @throws JSONException if there is an error parsing the JSON data
     */
    private Optional<WeeklyWorkload> getWorkload() throws JSONException {
        if (this.has(ModAttrJSONKey.WORKLOAD)) {
            JSONArray workloadJSONArray = this.getJSONArray(ModAttrJSONKey.WORKLOAD);

            return Optional.of(new WeeklyWorkload(
                    workloadJSONArray.getDouble(0),
                    workloadJSONArray.getDouble(1),
                    workloadJSONArray.getDouble(3),
                    workloadJSONArray.getDouble(4)));
        }

        return Optional.empty();
    }

}
