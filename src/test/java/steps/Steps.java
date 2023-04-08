package steps;

import io.cucumber.java.en.*;
import io.restassured.builder.RequestSpecBuilder;
import org.junit.Assert;
import utils.ApiUtils;
import utils.ConfigReader;
import utils.requestBodyCreation.PojoUtils;
import utils.responseValidation.ResponseValidator;

import static io.restassured.RestAssured.*;

public class Steps extends BaseStep{

    /*
        We declared 'requestBuild' in BaseSteps as static. It allows to use same RequestSpecification for each test step.
        In every step it adds new specification until sending request
     */


    @Given("Set base URL {string} for {string}")
    public void set_base_url_for(String url, String env) {
        BaseStep.env = env;
        log.debug("Test Environment is : " + env.toUpperCase());
        log.debug("Base Url is '{}':", ConfigReader.get(url,env));

        baseURI = ConfigReader.get(url,env);
    }

    @Given("Set authorization with Bearer Token on {string}")
    public void set_authorization_with_non_expired_bearer_token_on(String env) {
        //You can use that for set authorization. But for this dummy request we won't need
        //requestBuild = ApiUtils.setRequestAuthorization(env);
        requestBuild = new RequestSpecBuilder().build();
    }

    @And("Send request body with {string}")
    public void Send_request_body_with(String s) {
        PojoUtils.getInstance().createBody(s);
    }

    @When("Send {string} request to {string} endpoint")
    public void sendRequestTo(String httpReq, String endpoint) {
        // Instead of using endpoint directly in feature file, we use propertyName for endpoints in endpoints.properties file
        endpoint = ConfigReader.getEndpoint(endpoint);
        response = ApiUtils.sendRequest(given().spec(requestBuild).log().all(),httpReq,endpoint);
        response.then().log().all();
        log.debug("Request endpoint: '{}' ", endpoint);
    }

    @Then("Verify that status code is {int}")
    public void iVerifyReceiveValidResponseWith(int statusCode) {
        System.out.println(response.body().asString());

        if(statusCode == response.statusCode()){
            log.debug("Expected response status code: " + statusCode);
            log.info("Response code: " + response.statusCode());
        }else {
            log.debug("Expected response status code: " + statusCode);
            log.error("Response code: " + response.statusCode());

        }
        Assert.assertEquals(statusCode,response.statusCode());
    }

    @And("Verify response body")
    public void verifyResponseBodyFor() {
        ResponseValidator validator = new ResponseValidator();
        validator.validation();
    }
}
