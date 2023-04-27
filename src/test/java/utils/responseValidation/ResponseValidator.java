package utils.responseValidation;

import pojos.product.AddProductResp;
import utils.ScenarioUtil;
import utils.mapsApi.AddPlaceResponseValidation;
import utils.productsApi.ProductResponseValidation;
import utils.requestBodyCreation.PojoUtils;

public class ResponseValidator {

    private static ResponseValidator responseValidator = new ResponseValidator();
    private static IResponseValidatable responseValidatable;

    private ResponseValidator(){}

    public static ResponseValidator getInstance(){return responseValidator;}

    public IResponseValidatable getResponseBody(){
        if (ScenarioUtil.getScenarioName().contains("Add Product")){
            return (responseValidatable == null) ? new ProductResponseValidation() : responseValidatable;
        }
        return (responseValidatable == null) ? new AddPlaceResponseValidation() : responseValidatable;
    }

    /**
     * We use scenarioName to avoid using unnecessary parameter in feature file
     */
    public void validation(){
        getResponseBody().responseValidation();
    }
}
