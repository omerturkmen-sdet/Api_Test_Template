package utils.requestBodyCreation;

import io.restassured.specification.RequestSpecification;

public interface IBodyCreatable {

    RequestSpecification createBody(RequestSpecification spec, String scenarioKey, String... key);
    void changeData(String... key);
}
