package by.poezdrw.config;

import by.poezdrw.util.PropertiesLoader;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class DriverManager {

    private static final Logger LOGGER = LogManager.getLogger(DriverManager.class);

    //Browser_del config from Properties file:
    private final static String BROWSER_NAME = "browser.name";
    //private final static String BROWSER_VERSION = "browser.version";
    //private final static String BROWSER_PLATFORM = "browser.platform";

    //Grid URL if preset
    private final static String GRID_HUB_URL = "grid.hub";

    public static WebDriver defineWebDriver() {
        String gridUrl = PropertiesLoader.getPropertyValue(GRID_HUB_URL);
        String browserName = PropertiesLoader.getPropertyValue(BROWSER_NAME);

        // set Chrome_local by default
        DriverType driverType = DriverType.CHROME;

        //if local driver chosen by Maven profile
        if (gridUrl == null || gridUrl.equalsIgnoreCase("")) {
            try {
                driverType = DriverType.valueOf(browserName.toUpperCase());
            } catch (IllegalArgumentException e) {
                LOGGER.log(Level.ERROR, "Invalid BrowserName passed as a parameter to make Driver, Chrome was started: " + e);
            }
            return driverType.initialize();
        }

        //if remote driver chosen
        else {
            // set Chrome_remote by default
//                    DesiredCapabilities capabilities = DesiredCapabilities.chrome();
            try {
                driverType = DriverType.valueOf(browserName.toUpperCase());
                return driverType.initialize(new URL(gridUrl));
            } catch (IllegalArgumentException | MalformedURLException e) {
                LOGGER.log(Level.ERROR, "Invalid argument passed as a parameter to make Driver, Chrome local was started: " + e);
                return driverType.initialize();
            }
        }
    }

    // TODO: Driver init doesn't work for Linux local machine, should change FileSeparator and give another drivers for init in Linux
    enum DriverType {

        FIREFOX {

            private static final String DRIVER_NAME = "webdriver.gecko.driver";
            private DesiredCapabilities capabilities = DesiredCapabilities.firefox();

            @Override
            public WebDriver initialize() {
                System.setProperty(DRIVER_NAME, PropertiesLoader.getPropertyValue(DRIVER_NAME));
                // additional capabilities init
                capabilities.setJavascriptEnabled(true);
                FirefoxDriver driver = new FirefoxDriver(capabilities);
                driver.manage().window().maximize(); // works on FF54 + Gecko 0_17
//                driver.manage().window().setSize(new Dimension(1920, 1080));
                return driver;
        }

            @Override
            public WebDriver initialize(URL gridHubUrl) {
                // additional capabilities init
                capabilities.setJavascriptEnabled(true);
                RemoteWebDriver driver = new RemoteWebDriver(gridHubUrl, capabilities);
                driver.manage().window().maximize(); // works on FF54 + Gecko 0_17
//                driver.manage().window().setSize(new Dimension(1920, 1080));
                return driver;
            }
        },

        CHROME {

            private static final String DRIVER_NAME = "webdriver.chrome.driver";
            private DesiredCapabilities capabilities = DesiredCapabilities.chrome();

            @Override
            public WebDriver initialize() {
                System.setProperty(DRIVER_NAME, PropertiesLoader.getPropertyValue(DRIVER_NAME));
                // additional capabilities init
                capabilities.setJavascriptEnabled(true);
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--start-maximized");
                capabilities.setCapability(ChromeOptions.CAPABILITY, options);

                return new ChromeDriver(capabilities);
            }

            @Override
            public WebDriver initialize(URL gridHubUrl) {
                // additional capabilities init
                capabilities.setJavascriptEnabled(true);
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--start-maximized");
                capabilities.setCapability(ChromeOptions.CAPABILITY, options);

                return new RemoteWebDriver(gridHubUrl, capabilities);
            }
        };

        public abstract WebDriver initialize();

        public abstract WebDriver initialize(URL gridHubUrl);
    }

}
