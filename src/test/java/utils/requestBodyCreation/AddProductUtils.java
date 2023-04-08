package utils.requestBodyCreation;

import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import pojos.product.AddProductRequest;
import utils.ApiUtils;
import utils.JsonDataReader;

public class AddProductUtils {

    public AddProductRequest addProductRequest = new JsonDataReader().getAddProductRequest();
    public void addProductRequestBody(RequestSpecification requestBuild, String scenario) {
        addProductRequest.setTitle("Added at " + ApiUtils.getTimeStamp());
        requestBuild.body(addProductRequest).contentType(ContentType.JSON);
    }
}
