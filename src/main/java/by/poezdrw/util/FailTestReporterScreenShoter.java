package by.poezdrw.util;

import by.poezdrw.config.DriverFactory;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.TestListenerAdapter;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Class to write report on failed tests and make screenshots for them
 */
//Instead of implementing ITestListener  and overriding all the methods - extending of TestListenerAdapter.class
public class FailTestReporterScreenShoter extends TestListenerAdapter {

    private static final Logger RP_SCREENSHOT_LOGGER = LogManager.getLogger("RP_binary"); //for saving screenshot to ReportPortal
    private static final Logger LOGGER = LogManager.getLogger(FailTestReporterScreenShoter.class); //for other data

    private static final String ESCAPE_PROPERTY = "org.uncommons.reportng.escape-output";
    private static final SimpleDateFormat FORMATER = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");

    @Override
    public void onTestFailure(ITestResult result) {

        super.onTestFailure(result);
        makeScreenshot(result);
    }

    @Override
    public void onTestSkipped(ITestResult result) {

        super.onTestSkipped(result);
        makeScreenshot(result);
    }

    @Override
    public void onConfigurationFailure(ITestResult result) {

        super.onConfigurationFailure(result);
        makeScreenshot(result);
    }

    private void makeScreenshot(ITestResult result) {
        File file = new File("");
        Reporter.setCurrentTestResult(result);
        Reporter.log(file.getAbsolutePath()); //log dir. of project running (by default on ,my local machine D:\Idea Workspace\trainproject)

        LOGGER.log(Level.ERROR, "Test " + result.getMethod().getMethodName() + " failed.");

        Calendar calendar = Calendar.getInstance();
        String methodName = result.getName();
        if (!result.isSuccess()) {
            File scrFile = ((TakesScreenshot) DriverFactory.getInstance()
                    .getDriver())
                    .getScreenshotAs(OutputType.FILE);
            try {
                //tell the Reporter not to escape HTML tags
                System.setProperty(ESCAPE_PROPERTY, "false");
                String reportDirectory = new File(System.getProperty("user.dir")).getAbsolutePath()
                        + File.separator + "target" + File.separator + "surefire-reports";
                String fileName = methodName + "_" + FORMATER.format(calendar.getTime()) + ".png";
                File destFile = new File((String) reportDirectory + File.separator + "failure_screenshots"
                        + File.separator + fileName);
                FileUtils.copyFile(scrFile, destFile);
                //save img for ReportNG:
                Reporter.log("<a href='" + destFile.getAbsolutePath() + "'> <img src='" + destFile.getAbsolutePath() + "' height='100' width='100'/> </a>");
                //save img for ReportPortal:
                RP_SCREENSHOT_LOGGER.log(Level.ERROR, "RP_MESSAGE#FILE#{}#{}", destFile, fileName, "screenshot for Fail test");
            } catch (IOException e) {
                LOGGER.log(Level.ERROR, "Unable to save screenshot for test failed: " + result.getName());
            }
        }
    }
}
