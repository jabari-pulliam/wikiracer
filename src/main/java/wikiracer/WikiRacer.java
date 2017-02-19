package wikiracer;


import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

public class WikiRacer {

    public static void main(String[] args) {
        WikiRacer racer = new WikiRacer(args[0]);
        System.out.println(racer.findPath());
    }

    private final InputSpec inputSpec;

    public WikiRacer(String jsonInput) {
        inputSpec = new InputParser().parseInputSpec(jsonInput);
    }

    public String findPath() {
        List<String> path = new PathFinder().findPath(inputSpec.getStart(),
                inputSpec.getEnd());

        JSONObject json = new JSONObject();
        json.put("start", inputSpec.getStart());
        json.put("end", inputSpec.getEnd());
        json.put("path", new JSONArray(path));

        return json.toString();
    }

}
