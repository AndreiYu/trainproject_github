package poezdrw.testNG.testbase;

import by.poezdrw.config.DriverFactory;
import by.poezdrw.util.FailTestReporterScreenShoter;
import com.epam.reportportal.testng.ReportPortalTestNGListener;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.*;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.Listeners;
import org.uncommons.reportng.HTMLReporter;
import org.uncommons.reportng.JUnitXMLReporter;

import java.lang.invoke.MethodHandles;

@Listeners({FailTestReporterScreenShoter.class, HTMLReporter.class,
        JUnitXMLReporter.class, AbstractTest.LogListener.class, ReportPortalTestNGListener.class})
public abstract class AbstractTest {

    private static Logger LOGGER = LogManager.getLogger(MethodHandles.lookup().lookupClass());

    //Webdriver init is done on AbstractPage

    //close browser after method executed
    @AfterMethod
    public void tearDown() {
        DriverFactory.getInstance().removeDriver();
        LOGGER.log(Level.INFO, "Webdriver killed");
    }

    //Kill all drivers after suite completed
    @AfterSuite (alwaysRun = true)
    public void finishSuite() {
        DriverFactory.closeDriverObjects();
        LOGGER.log(Level.INFO, "All Webdrivers killed");
    }


// ======== Listener for logging names of started and finished methods ================= //
// made private static to access LOGGER of AbstractTest directly (instead of passing it as method parameter)
    public static class LogListener extends TestListenerAdapter implements IInvokedMethodListener {

        //For suite invoked
        @Override
        public void onStart(ITestContext testContext) {
            super.onStart(testContext);
            LOGGER.log(Level.INFO, ">>> @TestSuite: " + testContext.getSuite().getName()  + " started.");
        }

        //For suite invoked
        @Override
        public void onFinish(ITestContext testContext) {
            super.onFinish(testContext);
            LOGGER.log(Level.INFO, "<<< @TestSuite: " + testContext.getSuite().getName() + "");
        }

        //For methods invoked
        @Override
        public void beforeInvocation(IInvokedMethod method, ITestResult testResult) {
            LOGGER.log(Level.INFO, ">>> @Test " + method.getTestMethod().getMethodName() + " started.");
        }

        //For methods invoked
        @Override
        public void afterInvocation(IInvokedMethod method, ITestResult testResult) {
            LOGGER.log(Level.INFO, "<<< @Test " + method.getTestMethod().getMethodName() + " finished." +
                    " Success result: " + testResult.isSuccess());
        }
    }


}
