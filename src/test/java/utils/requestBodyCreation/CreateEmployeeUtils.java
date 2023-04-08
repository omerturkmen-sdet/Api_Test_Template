package utils.requestBodyCreation;

import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import pojos.employee.EmployeeCreateRequest;
import utils.ApiUtils;
import utils.JsonDataReader;

import java.util.Random;

public class CreateEmployeeUtils {

    private EmployeeCreateRequest employeePojo = new JsonDataReader().getEmployeeCreateRequest();

    public void createEmployeeRequestBody(RequestSpecification requestBuild, String scenario, String... additionalKey) {
        //We will send our pojo object as request body.
        requestBuild.body(createEmployee(scenario)).contentType(ContentType.JSON);
    }

    private EmployeeCreateRequest createEmployee(String scenario) {
        //You can use parameter to send different body
        if (scenario.contains("valid")) {
            int random = new Random().nextInt(150) + 15;
            employeePojo.setName(scenario + ApiUtils.getTimeStamp());
            employeePojo.setAge(String.valueOf(random));
            employeePojo.setSalary(String.valueOf(random * 150));
        } else if (scenario.contains("invalid")) {
            employeePojo.setName(null);
        }
        return employeePojo;
    }
}
