package helpers;

import com.jayway.jsonpath.JsonPath;
import com.jayway.restassured.response.Response;
import net.minidev.json.JSONArray;

public class JsonPathHelper {

    public static <T> T read(Response response, String jsonPathExpression) {
        Object result = JsonPath.parse(response.getBody().asString()).read(jsonPathExpression);

        if (result instanceof JSONArray) {
            return (T) ((JSONArray) result).get(0);
        }

        return (T) result;
    }
}
