package utils.responseValidation;

import steps.BaseStep;
import utils.ScenarioUtil;

public class ResponseValidator extends BaseStep {

    /**
     * We use scenarioName to avoid using unnecessary parameter in feature file
     */
    public void validation(){
        if (ScenarioUtil.getScenarioName().contains("Create Employee")){
            log.info("Create Employee Response Assertion Started");
            new EmployeeResponseValidation().employeeResponseValidation();
        } else if (ScenarioUtil.getScenarioName().contains("Product")) {
            new ProductResponseValidation().productResponseValidation();
        }
        //You can add new validation for different requests
    }
}
