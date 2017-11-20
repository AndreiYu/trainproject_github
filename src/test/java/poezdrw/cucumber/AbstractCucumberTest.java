package poezdrw.cucumber;

import by.poezdrw.config.DriverFactory;
import by.poezdrw.core.ui.page.AbstractPage;
import by.poezdrw.util.FailTestReporterScreenShoter;
import cucumber.api.java.After;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.Listeners;
import org.uncommons.reportng.HTMLReporter;
import org.uncommons.reportng.JUnitXMLReporter;

import java.lang.invoke.MethodHandles;

@Listeners({FailTestReporterScreenShoter.class, HTMLReporter.class,
        JUnitXMLReporter.class})
public abstract class AbstractCucumberTest {

    protected static AbstractPage page;


//TODO: try to use Dependency injection instead of static field
//example: http://www.thinkcode.se/blog/2017/08/16/sharing-state-between-steps-in-cucumberjvm-using-guice

}
