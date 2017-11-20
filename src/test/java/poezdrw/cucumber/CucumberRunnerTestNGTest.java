package poezdrw.cucumber;


import by.poezdrw.config.DriverFactory;
import cucumber.api.CucumberOptions;
import cucumber.api.SnippetType;
import cucumber.api.testng.AbstractTestNGCucumberTests;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;

import java.lang.invoke.MethodHandles;

//or StepReporter
@CucumberOptions(plugin = {"pretty", "com.epam.reportportal.cucumber.ScenarioReporter"},
        features = "src/test/resources/cucumber",
        glue = {"poezdrw.cucumber.stepdef"},
//        tags = "@all",
        dryRun = false,
        strict = false,
        snippets = SnippetType.CAMELCASE
//        name = "^Успешное|Успешная.*"
)
public class CucumberRunnerTestNGTest extends AbstractTestNGCucumberTests {

    private static Logger LOGGER = LogManager.getLogger(MethodHandles.lookup().lookupClass());

    //close browser after method executed
    @AfterClass
    public void tearDown() {
        DriverFactory.getInstance().removeDriver();
        LOGGER.log(Level.INFO, "Webdriver killed");
    }

    //Kill all drivers after suite completed
    @AfterSuite(alwaysRun = true)
    public void finishSuite() {
        DriverFactory.closeDriverObjects();
        LOGGER.log(Level.INFO, "All Webdrivers killed");
    }

}
