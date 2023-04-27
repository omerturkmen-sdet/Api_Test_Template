package utils.productsApi;

import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import pojos.Pojo;
import pojos.product.AddProductReq;
import utils.JsonDataReader;
import utils.requestBodyCreation.IBodyCreatable;

public class AddProductCreator implements IBodyCreatable {

    private Pojo requestBody = new JsonDataReader(new AddProductReq()).getJson();
    @Override
    public RequestSpecification createBody(RequestSpecification spec, String scenarioKey, String... key) {
        return spec.body(requestBody).contentType(ContentType.JSON);
    }

    @Override
    public void changeData(String... key) {

    }
}
