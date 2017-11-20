package by.poezdrw.core.ui.page;

import by.poezdrw.helper.Randomiser;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.*;

public class DataOnPassengersPage extends SchedulesAndFaresPage {

    @FindBy(xpath = "//select[contains(@id,'countAdults')]")
    private WebElement adultCountPassengers;

    @FindBy(xpath = "//select[contains(@id,'countChildren')]")
    private WebElement childCountPassengers;

    @FindBy(xpath = "//select[contains(@id,'countChildrenNoPlaces')]")
    private WebElement countChildrenNoPlaces;

    @FindBy(xpath = "//a[contains(@id,'pass:tableEx1')]")
    private WebElement linkListSelectingPassengerFromPreviouslyCompletedOrder;

    @FindBy(xpath = "//tbody//a[contains(@onclick,'btSetPassInfo')]")
    private List<WebElement> allLastNamePassengersPreviousOrder;

    @FindBy(xpath = "//table[contains(@id,'tableEx')]")
    private List<WebElement> listTablePassengersData;

    /*@FindBy(xpath = "//span[contains(text(),'Взрослый')]")
    private WebElement typeTicket;*/

    @FindBy(xpath = "//input[contains(@id,'lastname')]")   //lastname
    private WebElement lastnameField;   //if > 1 users were selected returns first found. Refactor later for all users

    @FindBy(xpath = "//input[contains(@id,':name')]") // name
    private WebElement firstNameField; //if > 1 users were selected returns first found. Refactor later for all users

    @FindBy(xpath = "//input[contains(@id,'docNum')]")
    private WebElement docNumberField;  // passport Number //if > 1 users were selected returns first found. Refactor later for all users

    @FindBy(xpath = "//div//label[contains(text(),'Выбор мест на схеме вагона')]")
    private WebElement buttonChoicePlacesOnTheSchemeOfTheCarriage;

    @FindBy(xpath = "//div//label[contains(text(),'Требования к местам')]")
    private WebElement buttonRequirementsForPlaces;

    @FindBy(xpath = "//input[contains(@value,'Назад')]")
    private WebElement toThePreviousPageButton;

    @FindBy(xpath = "//input[contains(@value,'В начало')]")
    private WebElement toTheBeginingButton;

    @FindBy(xpath = "//div[contains(@id,'modelDiv')]//a")
    private List<WebElement> allAvailableSeatsInTheCarriageByDiagram;

    @FindBy(xpath = "//table//tbody[contains(@id,'tbody_element')]//a//span")
    private List<WebElement> allAvailableSeatsByRequirements;

    @FindBy(xpath = "//input[contains(@type,'checkbox') and contains(@id,'pass:conf')]")
    private WebElement agreeWithTermsAndConditionsCheckbox;

    // webelements for assertion:
    @FindBy(xpath = "//span[contains(@id,'_16c98d04')]")
    private WebElement trainNumberPreviouslyChosen;

    @FindBy(xpath = "//span[contains(@id,'_16c98e7d')]")
    private WebElement carriageNumberPreviouslyChosen;

    @FindBy(xpath = "//div[@class='seatSel']")
    private WebElement placeNumberChosenByClick;

    @FindBy(xpath = "//table[contains(@id,'tableEx1:0')]//input[@value='M']")
    private WebElement radioButtonChoiceGender; // filling in the name and passport data

    @FindBy(xpath = "//table[contains(@id,'typeMW')]//input[@value='M']")
    private WebElement radioButtonChoiceGenderDiagramCarriageAndRequirement;


    private ArrayDeque<String> randomSeatsChosenByClick = new ArrayDeque<>();

    //to refactor later
    public DataOnPassengersPage fillSinglePassengerData(String lastname, String firstname, String passportNumber) {
        sendKeys(lastnameField, lastname);
        sendKeys(firstNameField, firstname);
        sendKeys(docNumberField, passportNumber);

        if (isElementPresent(radioButtonChoiceGender)) {
            click(radioButtonChoiceGender);
        }

        return this;
    }


    public String getLastNamePassenger() {
        return getAttribute(lastnameField,"value");
    }

    public String getFirstNamePassenger() {
        return getAttribute(firstNameField,"value");
    }

    public String getDocNumber() {
        return getAttribute(docNumberField,"value");
    }


    public DataOnPassengersPage selectPassengerFromPreviouslyCompletedOrder() {
        click(linkListSelectingPassengerFromPreviouslyCompletedOrder);
        waitForFrameAvailableAndSwitchToIt("frm");
        click(Randomiser.getRandomWebElementValue(allLastNamePassengersPreviousOrder));
        driver.switchTo().defaultContent();

        if (isElementPresent(radioButtonChoiceGender)) {
            click(radioButtonChoiceGender);
        }

        return this;
    }

    public DataOnPassengersPage chooseAnyRandomPlace() {
        if (isElementPresent(radioButtonChoiceGenderDiagramCarriageAndRequirement)) {
            click(radioButtonChoiceGenderDiagramCarriageAndRequirement);
        }
        if (allAvailableSeatsInTheCarriageByDiagram.size() > 0) {
            selectRandomAvailableSeat(allAvailableSeatsInTheCarriageByDiagram);
        }
        else {
            if (allAvailableSeatsByRequirements.size() > 0) {
                selectRandomAvailableSeat(allAvailableSeatsByRequirements);
            }
            else {
                randomSeatsChosenByClick.offer("нет");
            }
        }

        return this;
    }

    private DataOnPassengersPage selectRandomAvailableSeat(List<WebElement> webElement) {

        WebElement selectedSeat = Randomiser.getRandomWebElementValue(webElement);
        randomSeatsChosenByClick.offer(selectedSeat.getText());
        mouseClick(selectedSeat);

        return this;
    }

    public CheckingOrderPage clickAcceptTermsAndConditionsCheckbox() {
        click(agreeWithTermsAndConditionsCheckbox);
        return new CheckingOrderPage();
    }


    //Methods for assertion
    public String getTrainNumber() {
        return read(trainNumberPreviouslyChosen);
    }

    public String getCarriageNumber() {
        return read(carriageNumberPreviouslyChosen);
    }

    public String getAndRemovePlaceNumberChosenByClick() {
        return randomSeatsChosenByClick.poll();
    }

    public String getAndKeepPlaceNumberChosenByClick() {
        return randomSeatsChosenByClick.peek();
    }


}

