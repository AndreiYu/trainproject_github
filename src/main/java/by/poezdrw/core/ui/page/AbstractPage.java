package by.poezdrw.core.ui.page;

import by.poezdrw.config.DriverFactory;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.lang.invoke.MethodHandles;
import java.util.concurrent.TimeUnit;
//========================DONE=====================//

public abstract class AbstractPage {

    private static Logger LOGGER = LogManager.getLogger(MethodHandles.lookup().lookupClass()); //for ability to use separate loggers for inherited Page classes - create anonimous class and get enclosing class of it

    protected WebDriver driver;

    private static final int WAIT_ELEMENT_TIMEOUT = 10;
    private static final int WAIT_CUSTOM_ELEMENT_TIMEOUT = 1;

    /**
     * Init driver and elements for PageFactory
     */
    public AbstractPage() {
        this.driver = DriverFactory.getInstance().getDriver();
        this.driver
                .manage()
                .timeouts()
                .pageLoadTimeout(WAIT_ELEMENT_TIMEOUT, TimeUnit.SECONDS);
        PageFactory.initElements(this.driver, this);
//        if (this.getClass() != AbstractPage.class) {    //Avoid printing Log when chain of constructors
            LOGGER.log(Level.INFO, "Access to " + this.getClass().getName() + " page");
//        }
    }

    public WebElement getElement(WebElement element) {
        waitForElementVisible(element);
        return element;
    }

    //del if not used
    public String getTitle() {
        return this.driver.getTitle();
    }

    /**
     * Method for opening the page by providing it's URL
     *
     * @param url URL of this page
     * @return this page
     */
    public AbstractPage open(String url) {
        driver.get(url);
        LOGGER.log(Level.INFO, "Opening webPage: " + url);
        return this;
    }

    /*
    Method to check if element is present and visible. Accepts WebElement, not @By class,
    so if it's not present - catch Exception and return false
     */
    public boolean isElementPresent(WebElement webElement) {
        boolean result = false;
        try {
            waitForCustomElementVisible(webElement);
            result = webElement.isDisplayed();
        } catch (NoSuchElementException | TimeoutException e) {
            LOGGER.log(Level.DEBUG, "Searched WebElement not found, it's OK, continue executing");
        }
        return result;
    }

    public boolean isValidPageLoaded(WebElement attribute) {
            return isElementPresent(attribute);
    }

    /**
     * Method to check if valid page is loaded providing String text containing in WebElement
     */
    public boolean isValidPageLoaded(WebElement attribute, String text) {
           return isElementPresent(attribute)
                    && attribute.getText().equalsIgnoreCase(text);
    }

    public void click(WebElement element) {
        waitForElementClickable(element);
        LOGGER.log(Level.INFO, "Clicking WebElement: tagName: [" + element.getTagName() +
                "] at Location Point: [" + element.getLocation() + "]");
        highlightElement(element);
        element.click();
        try {
            unhighlightElement(element);  //if on the same page, unhighlight element
        } catch (StaleElementReferenceException | NoSuchElementException e) {
            //do nothing if already gone to another page
        }
    }

    /**
     * Imitate mouse focus and mouse over before click (for Javascript accompanied actions on elements)
     * @param element
     */
    public void mouseClick(WebElement element) {
        waitForElementClickable(element);
        LOGGER.log(Level.INFO, "Imitating Mouse clicking WebElement: tagName: [" + element.getTagName() +
                "] at Location Point: [" + element.getLocation() + "]");
        highlightElement(element);
        new Actions(this.driver).moveToElement(element).
                click()
                .build()
                .perform();
        new Actions(this.driver).moveToElement(element, 100, 100)
                .build()
                .perform();
        try {
            unhighlightElement(element);  //if on the same page, unhighlight element
        } catch (StaleElementReferenceException | NoSuchElementException e) {
            //do nothing if already gone to another page
        }
    }

    public void clean(WebElement element) {
        waitForElementVisible(element);
        LOGGER.log(Level.INFO, "Cleaning WebElement: tagName: [" + element.getTagName() +
                "] at Location Point: [" + element.getLocation() + "]");
        highlightElement(element);
        element.clear();
        unhighlightElement(element);  //if on the same page, unhighlight element
    }

    public void type(WebElement element, String text) {
        waitForElementVisible(element);
        LOGGER.log(Level.INFO, "Typing text: [" + text + "] in WebElement: [" + element.getAttribute("name")
                + "] located at Point: [" + element.getLocation() + "]");
        highlightElement(element);
        element.sendKeys(text);
        unhighlightElement(element);  //if on the same page, unhighlight element
    }

    public String read(WebElement element) {
        waitForElementVisible(element);
        LOGGER.log(Level.INFO, "Reading WebElement: tagName: [" + element.getTagName() +
                "] at Location Point: [" + element.getLocation() + "]");
        highlightElement(element);
        String text = element.getText();
        unhighlightElement(element);  //if on the same page, unhighlight element
        return text;
    }

     public void sendKeys(WebElement element, String text) {
        waitForElementVisible(element);
        LOGGER.log(Level.INFO, "Sending keys: [" + text + "] to WebElement tagName: [" + element.getTagName() +
                "] at Location Point: [" + element.getLocation() + "]");
        highlightElement(element);
        element.clear();
        element.sendKeys(text);
        unhighlightElement(element); //works if stays on the same page after sending keys
    }

    //Works only for select. If @Option is passed - click() should be used
    public void select(WebElement element, String text) {
        waitForElementClickable(element);
        LOGGER.log(Level.INFO, "Reading WebElement: tagName: [" + element.getTagName() +
                "] at Location Point: [" + element.getLocation() + "]");
        highlightElement(element);
        Select select = new Select(element);
        select.selectByVisibleText(text);
        unhighlightElement(element);  //works if stays on the same page after selection
    }

    public String getAttribute(WebElement element, String name) {
        waitForElementVisible(element);
        LOGGER.log(Level.INFO, "Getting attribute: [" + name + "] from WebElement: tagname: [ " +
                element.getTagName() + "] at location Point: [" + element.getLocation() + "]");
        highlightElement(element);
        String attribute = element.getAttribute(name);
        unhighlightElement(element);
        return attribute;
    }


    //   Custom waiters   //
    //*************************************************************************************//

    protected void waitForElementVisible(WebElement element) {
        new WebDriverWait(driver, WAIT_ELEMENT_TIMEOUT)
                .until(ExpectedConditions.visibilityOf(element));
    }

    protected void waitForCustomElementVisible(WebElement element) {
        new WebDriverWait(driver, WAIT_CUSTOM_ELEMENT_TIMEOUT)
                .until(ExpectedConditions.visibilityOf(element));
    }

    protected void waitForElementClickable(WebElement element) {
        new WebDriverWait(driver, WAIT_ELEMENT_TIMEOUT)
                .until(ExpectedConditions.elementToBeClickable(element));
    }

    protected void waitForFrameAvailableAndSwitchToIt(String frameName) {
        new WebDriverWait(driver, WAIT_ELEMENT_TIMEOUT)
                .until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameName));
    }

    //  Highlighter //
    //************************************************************************************************//

    private void highlightElement(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].setAttribute('style', 'border: 3px solid yellow;');", element);
    }

    private void unhighlightElement(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].setAttribute('style', '');", element);
    }


}
