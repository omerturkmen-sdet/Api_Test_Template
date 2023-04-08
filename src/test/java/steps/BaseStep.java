package steps;

import helpers.LogHelper;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.slf4j.Logger;

import java.util.HashMap;
import java.util.Map;

public class BaseStep {

    public static RequestSpecification requestBuild;
    public static Response response;
    Map<String,Object> map = new HashMap<>();
    static String env = "dev";
    static public Logger log = LogHelper.getLogger();
}
