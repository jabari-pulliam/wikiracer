package wikiracer;

import org.json.JSONObject;

public class InputParser {

    public InputSpec parseInputSpec(String jsonInput) {
        // Parse the JSON input
        JSONObject inputSpec = new JSONObject(jsonInput);
        String start = inputSpec.getString("start");
        String end = inputSpec.getString("end");
        return new InputSpec(start, end);
    }

}
