package utils.mapsApi;

import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import pojos.Pojo;
import pojos.map.AddPlaceReq;
import utils.JsonDataReader;
import utils.requestBodyCreation.IBodyCreatable;

public class AddPlaceBodyCreator implements IBodyCreatable {

    private AddPlaceReq requestBody = (AddPlaceReq) new JsonDataReader(new AddPlaceReq()).getJson();
    @Override
    public RequestSpecification createBody(RequestSpecification spec, String scenarioKey, String... key) {
        return spec.body(requestBody).contentType(ContentType.JSON);
    }

    @Override
    public void changeData(String... key) {
    }
}
