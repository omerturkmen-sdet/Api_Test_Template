package utils.requestBodyCreation;

import steps.BaseStep;
import utils.ScenarioUtil;
import utils.mapsApi.AddPlaceBodyCreator;
import utils.productsApi.AddProductCreator;

public class PojoUtils{

    private static PojoUtils pojoUtils = new PojoUtils();
    private static IBodyCreatable bodyCreatable;

    // Declared private for singleton design
    private PojoUtils(){}

    public static PojoUtils getInstance(){
        return pojoUtils;
    }

    // To avoid creating new object
    public IBodyCreatable getBodyCreatable(){
        if (ScenarioUtil.getScenarioName().contains("Add Place")){
            return (bodyCreatable == null) ? new AddPlaceBodyCreator() : bodyCreatable;
        } else{
            return (bodyCreatable == null) ? new AddProductCreator() : bodyCreatable;
        }
    }


    /**
     *
     * @param scenario : this came from feature file to specify api request
     */
    public void createBody(String scenario){
        getBodyCreatable().createBody(BaseStep.requestBuild,scenario);
    }

    /**
     *
     * @param scenario
     * @param additionalKey : this came from feature file to make modification while creating request body
     */
    public void createBody(String scenario, String additionalKey){
        getBodyCreatable().createBody(BaseStep.requestBuild,scenario,additionalKey);
    }
}
