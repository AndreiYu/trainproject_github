package by.poezdrw.config;

import org.openqa.selenium.WebDriver;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DriverFactory {

    // Pool for storing all instances of WebDriver
    private static List<WebDriver> webDriverPool = Collections.synchronizedList(new ArrayList<WebDriver>());
    private static DriverFactory instance = new DriverFactory();

    private DriverFactory() {

    }

    public static DriverFactory getInstance()
    {
        return instance;
    }

    ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>() {
        @Override
        protected WebDriver initialValue()
        {
            WebDriver driver = DriverManager.defineWebDriver();
            webDriverPool.add(driver);
            return driver;
        }
    };

    public WebDriver getDriver() {
        return driver.get();
    }

    // Quits the single driver and closes the browser
    public void removeDriver()
    {
        driver.get().quit();
        driver.remove();
    }

    // Quits all the drivers and closes the browsers
    public static void closeDriverObjects() {
        for (WebDriver webDriver : webDriverPool) {
            webDriver.quit();
        }
    }


}
