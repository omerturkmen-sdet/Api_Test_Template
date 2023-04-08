package utils.requestBodyCreation;

import steps.BaseStep;

public class PojoUtils{

    private static PojoUtils pojoUtils = new PojoUtils();
    private static CreateEmployeeUtils createEmployeeUtils;
    private static AddProductUtils addProductUtils;

    // Declared private for singleton design
    private PojoUtils(){}

    public static PojoUtils getInstance(){
        return pojoUtils;
    }

    // To avoid creating new object
    public CreateEmployeeUtils getCreateEmployeeUtils(){
        return (createEmployeeUtils == null) ? new CreateEmployeeUtils() : createEmployeeUtils;
    }

    public AddProductUtils getAddProductUtils(){
        return (addProductUtils == null) ? new AddProductUtils() : addProductUtils;
    }

    /**
     *
     * @param scenario : this came from feature file to specify api request
     */
    public void createBody(String scenario){
        if (scenario.equals("valid employee creation")){
            getCreateEmployeeUtils().createEmployeeRequestBody(BaseStep.requestBuild,scenario);
        } else if (scenario.equals("add product")) {
            getAddProductUtils().addProductRequestBody(BaseStep.requestBuild,scenario);
        }
    }

    /**
     *
     * @param scenario
     * @param additionalKey : this came from feature file to make modification while creating request body
     */
    public void createBody(String scenario, String additionalKey){
        if (scenario.contains("dummy")){
            getCreateEmployeeUtils().createEmployeeRequestBody(BaseStep.requestBuild,scenario,additionalKey);
        }
    }
}
