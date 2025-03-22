package modmate.download.json;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ModJSONUtil {

    private final JSONObject jsonObject;

    public ModJSONUtil(JSONObject jsonObject) {
        this.jsonObject = jsonObject;
    }

    public String getString(ModJSONKey key) throws JSONException {
        return this.jsonObject.getString(key.toString());
    }

    public int getInt(ModJSONKey key) throws JSONException {
        return this.jsonObject.getInt(key.toString());
    }

    public JSONArray getJSONArray(ModJSONKey key) throws JSONException {
        return this.jsonObject.getJSONArray(key.toString());
    }

    public JSONObject getJSONObject() {
        return this.jsonObject;
        // I'M SORRY OKAY I just genuinely Don't understand this code im sorry 0_0 I'm very tired.
        // Cheating my way out of abstraction hell
    }

}
