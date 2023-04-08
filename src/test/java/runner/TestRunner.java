package runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.AfterClass;
import org.junit.runner.RunWith;
import utils.ApiUtils;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",
                "rerun:target/failedRerun.txt"},
        features = "src/test/resources/features",
        glue = "steps",
        tags = "@wip"
)
public class TestRunner {
    /**
     * When execution finish, runner class generates log file and report.
     * You can use @AfterClass annotation to do something after all process
     * In our situation we will use this method for sending our log file and report via mail
     * @throws Exception
     */
    @AfterClass
    public static void afterClass() throws Exception{
        //ApiUtils.sendingLogFileAndReportViaMail(ApiUtils.getMessageForMail());
    }
}
