package helper;


import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class KeyValuePairCollection {

    private List<String> keys = new ArrayList<String>();
    private List<String> values = new ArrayList<String>();

    public void addPair(String key, String value) {
        keys.add(key);
        values.add(value);
    }

    public String toJSON() {
        JSONObject result = new JSONObject();

        try {
            for (int i = 0; i < keys.size(); ++i) {
                result.accumulate(keys.get(i), values.get(i));
            }
        } catch (Exception ex) {

        }

        return result.toString();
    }

    public String toParams() {
        String result = "FakeParam=FakeParam";

        for (int i = 0; i < keys.size(); ++i) {
            result += "&" + keys.get(i) + "=" + values.get(i);
        }

        return result;
    }

}
