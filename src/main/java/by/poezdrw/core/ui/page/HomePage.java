package by.poezdrw.core.ui.page;

import by.poezdrw.util.PropertiesLoader;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

//========================DONE=====================//
public class HomePage extends AbstractPage {

    public static final String URL = PropertiesLoader.getPropertyValue("site.url");

/*    TODO: logged in user also can see "PersonalCabinet" and "Purchase Ticket" buttons
    but if test needs user to be logged in, it's done at the beginning, not in the middle of scenario...
    overwise custom elementPage "Head menu-1" and "Head menu-2" should be made to gain access to this buttons in the
    middle of testScenario*/

    @FindBy(xpath = "//a[contains(@onclick,'login_main')]")
    private WebElement buttonLoginAccount;

    @FindBy(xpath = "//a[@id='logoutlink']")
    private WebElement logoutText;

    @FindBy(xpath = "//a[contains(@href,'rp/schedule')]")
    private WebElement schedulesAndFaresButton;

    @FindBy(xpath = "//a[contains(@href,'feedbackMail')]")
    private WebElement buttonFeedBack;

    //only if user is logged in
    @FindBy(xpath = "//a[contains(@href,'home/rp/private')]")
    private WebElement personalCabinetButton;

    public WebElement getLogoutText() {
        return logoutText;
    }

    public HomePage openHomepage() {
        String url = PropertiesLoader.getPropertyValue("site.url");
        return (HomePage) super.open(url);
    }

    public LoginPage clickButtonLogin() {
//        LOGGER.log(Level.INFO,"Access to LoginPage");
        click(buttonLoginAccount);
        return new LoginPage();
    }

    //goes directly to this page after clicking schedulesAndFaresButton
    //only if user is logged in
    //TODO:check if logged in and don't return PersonalCabinetPage otherwise?
    public InfoAboutPlacesAvailableAndCostPage goToTicketBookingPage() {
//        LOGGER.log(Level.INFO,"Access to SchedulesAndFaresPage");
        click(schedulesAndFaresButton);
        return new InfoAboutPlacesAvailableAndCostPage();
    }

    public FeedBackMailPage goToFeedBackMailPage() {
//        LOG.info("click: 'FeedBack Button'");
        click(buttonFeedBack);
        return new FeedBackMailPage();
    }

    //only if user is logged in
    //TODO:check if logged in and don't return PersonalCabinetPage otherwise?
    public PersonalCabinetPage goToPersonalCabinetPage() {
//        LOG.info("click: 'Cabinet Button'");
        click(personalCabinetButton);
        return new PersonalCabinetPage();
    }

}
