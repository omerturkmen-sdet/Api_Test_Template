package steps;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.restassured.config.LogConfig;
import utils.ApiUtils;
import utils.ScenarioUtil;

import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.config;

public class Hooks extends BaseStep{

    Scenario scenario;
    public static List<String> scenarioMessages = new ArrayList<>();

    @Before
    public void setup(Scenario scenario){
        // It will print log to the file. It will clear file and print again for each request
        //ApiUtils.configRestAssuredLog();
        this.scenario = scenario;
    }

    @Before(order = 1)
    public void initiateScenario(Scenario scenario) {
        ScenarioUtil.setScenario(scenario);
    }
    @Before(order = 4)
    public void startScenario() {
        if (!ScenarioUtil.sameFeature()) {
            log.info("+++++ Feature '" + ScenarioUtil.getFeatureName().toUpperCase() + "' STARTED +++++");
        }
        log.info("Scenario '" + ScenarioUtil.getScenarioName() + "' STARTED");
    }

    @After
    public void tearDown(Scenario scenario){
        if (scenario.isFailed()){
            log.error("Scenario '{}' Failed.", scenario.getName());
            ExtentCucumberAdapter.getCurrentStep().log(Status.FAIL, MarkupHelper.createLabel("Response: ",ExtentColor.RED));
            ExtentCucumberAdapter.getCurrentStep().log(Status.FAIL, response.body().asString());

            // This will read file and add the file as log.
            String fileRead = ApiUtils.fileRead();
            log.debug(fileRead);
        }else {
            log.info("Scenario '{}' Passed.", scenario.getName());
        }

        scenarioMessages.add(scenario.getStatus().toString() + "\t Scenario: " + scenario.getName());
    }
}