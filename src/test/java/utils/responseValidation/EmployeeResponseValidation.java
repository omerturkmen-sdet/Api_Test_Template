package utils.responseValidation;

import com.google.gson.Gson;
import org.junit.Assert;
import pojos.employee.CreateEmployeeResponse;
import steps.BaseStep;
import utils.ApiMessageConstants;
import utils.ScenarioUtil;

import static org.junit.Assert.assertEquals;

public class EmployeeResponseValidation {

    Gson gson = new Gson();

    //This will convert response to pojo object
    CreateEmployeeResponse response = gson.fromJson(BaseStep.response.body().asString(), CreateEmployeeResponse.class);

    public void employeeResponseValidation() {
        Assert.assertEquals(ApiMessageConstants.validCreateEmployeeResponseMessage,response.getMessage());
        assertEquals("success", response.getStatus());

        //You can also add different validation without sending parameter

        if (ScenarioUtil.getScenarioName().contains("invalid")){
            assertEquals(ApiMessageConstants.invalidCreateEmployeeResponseMessage,response.getMessage());
        }
    }
}
