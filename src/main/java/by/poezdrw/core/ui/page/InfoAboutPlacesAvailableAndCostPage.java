package by.poezdrw.core.ui.page;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class InfoAboutPlacesAvailableAndCostPage extends SchedulesAndFaresPage {

    @FindBy(xpath = "//input[@type='text' and contains(@id,'textDepStat')]")
    private WebElement fromStationField;

    @FindBy(xpath = "//input[@type='text' and contains(@id,'textArrStat')]")
    private WebElement toStationField;

    @FindBy(xpath = "//input[contains(@class,'datepicker')]")
    private WebElement dateDepartmentField;

    @FindBy(xpath = "//span[contains(@id,'_5ed6129a')]")
    private WebElement linkChooseAllTimeDeparture;

    //TODO: change locator
    @FindBy(xpath = "//span[contains(text(),'Сбросить время')]")
    private WebElement linkResetTimeDeparture;

    @FindBy(xpath = "//select[contains(@id,'countAdults')]")
    private WebElement adultCountPassengers;

    @FindBy(xpath = "//select[contains(@id,'countChildren')]")
    private WebElement childCountPassengers;

    @FindBy(xpath = "//select[contains(@id,'countChildrenNoPlaces')]")
    private WebElement countChildrenNoPlaces;

    @FindBy(xpath = "//input[contains(@id,'buttonCancel')]")
    private WebElement buttonCancel;

    @FindBy(xpath = "//input[contains(@id,'buttonSearch')]")
    private WebElement submitButton;

    @FindBy(xpath = "//a[contains(@href,'javascript:changeSts()')]")
    private WebElement buttonChangeStation;

    @FindBy(xpath = "//span[contains(text(),'Выберите маршрут следования')]")
    private WebElement checkInformationPageValid;


    public InfoAboutPlacesAvailableAndCostPage enterFromToRoute(String fromStation, String toStation) {
        sendKeys(fromStationField, fromStation);
        sendKeys(toStationField, toStation);

        return this;
    }

    public InfoAboutPlacesAvailableAndCostPage enterDepartureDate(String date) {
        sendKeys(dateDepartmentField, date);
        click(linkChooseAllTimeDeparture);

        return this;
    }

    public InfoAboutPlacesAvailableAndCostPage enterAdultPassengersNumber(String numberAdultPassengers) {
        select(adultCountPassengers, numberAdultPassengers);

        return this;
    }


    public String getDepartStationEntered() {
        return getAttribute(fromStationField, "value");
    }

    public String getArrivalStationEntered() {
        return getAttribute(toStationField, "value");
    }

    public void clickSwapStationsButton() {
        click(buttonChangeStation);
    }



    public String getDepartureTimeEntered() {
        return getAttribute(dateDepartmentField, "value");
    }


    public void resetAllDataInPage() {
        click(buttonCancel);
    }

    public PurchaseTicketChoiceTrain continueToTrainChoice() {
        click(submitButton);
        return new PurchaseTicketChoiceTrain();
    }

    /**
     * Method for test NAME_ENTER
     * @param text
     * @return
     */
    public boolean isValidPageLoaded(String text) {
        return super.isValidPageLoaded(checkInformationPageValid, text);
    }


}
