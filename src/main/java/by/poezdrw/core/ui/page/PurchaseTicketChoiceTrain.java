package by.poezdrw.core.ui.page;

import by.poezdrw.helper.Randomiser;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;


//TODO refactor
public class PurchaseTicketChoiceTrain extends SchedulesAndFaresPage {

    @FindBy(xpath = "//table[@class='information' and contains(@id,'form2:tableEx1')]") // schedule train
    private WebElement trainScheduleTable;

    @FindBy(xpath = "//a[contains(@href,'javascript:btChangeDay(1)')]")
    private WebElement daysAheadButton;

    @FindBy(xpath = "//a[contains(@href,'javascript:btChangeDay(-1)')]")
    private WebElement daysAgoButton;

    @FindBy(xpath = "//table[@id='dateInfo']//input[contains(@id,'change:dob')]")
    private WebElement dateDepartment;

    @FindBy(xpath = "//input[@type='checkbox' and (contains(@onclick,':change:buttonSearch'))]")
    private WebElement onlyElectronicRegistrationCheckboxButton;

 /*   // @FindBy(xpath = "//*[@type='checkbox']")
    private WebElement departureTime;*/

    @FindBy(xpath = "//a[contains(@href,'javascript:btSelectAllColor(false')]")
    private WebElement resetTimeButton;

    @FindBy(xpath = "//a[contains(@href,'javascript:btSelectAllColor(true')]")
    private WebElement chooseAllTimeButton;

    @FindBy(xpath = "//input[contains(@id,'_d00d06e') and (@type='submit')]")
    private WebElement toTheBeginingButton;

    @FindBy(xpath = "//input[contains(@onclick,'btPrintSess') and (@type='button')]")
    private WebElement printingScheduleTrain;

    // ALL radio button in schedule train result table
    @FindBy(xpath = "//input[@type='radio' and contains(@id,'form2:tableEx1')]")
    private List<WebElement> allTrainsFoundRadioButtons;

    @FindBy(xpath = "//table[@class='inlist']//span[@onmouseout]")
    private List<WebElement> allTrainWithElectronicRegistrationLabels;

    @FindBy(xpath = "//table[@class='information']//td//a")
    private List<WebElement> allTrainFoundNames;

    @FindBy(xpath = "//table[@class='information']//td//a[not(@class='elcol')]")     // all train without electronic registration
    private List<WebElement> allTrainsWithoutElectronicRegistrationNames;

    @FindBy(xpath = "//table[@class='information']//td//a[@class='elcol']")  // all train with electronic registration
    private List<WebElement> allTrainsWithElectronicRegistrationNames;

    public PurchaseTicketChoiceTrain enterDepartureDate(String date) {
        sendKeys(dateDepartment, date);
        return this;
    }


    public InfoOnCarriagesPage chooseAnyTrain() {
        click(Randomiser.getRandomWebElementValue(allTrainsFoundRadioButtons));
        return new InfoOnCarriagesPage();
    }



}

