package by.poezdrw.core.ui.page;

import by.poezdrw.core.ui.element.WebTable;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
//========================DONE=====================//
public class InfoAboutTrainSchedulePage extends SchedulesAndFaresPage {

    @FindBy(xpath = "//input[@type='text' and contains(@id,'textDepStat')]")
    private WebElement fromStationField;

    @FindBy(xpath = "//input[@type='text' and contains(@id,'textArrStat')]")
    private WebElement toStationField;

    @FindBy(xpath = "//input[contains(@class,'datepicker')]")
    private WebElement dateDepartmentField;

    @FindBy(xpath = "//input[contains(@id,'buttonSearch')]")
    private WebElement submitButton;

    @FindBy(xpath = "//input[contains(@id,'buttonCancel')]")
    private WebElement buttonCancel;

    // for assertion
    @FindBy(xpath = "//span[contains(@id, 'textRoute')]")
    private WebElement routeTextFound;

    @FindBy(xpath = "//span[contains(@id, '_d00d54d')]")
    private WebElement dateTextFound;

    @FindBy(xpath = "//table[contains(@id, 'form2:tableEx1_')]")
    private WebElement tableResultTrains;

    private WebTable tableFoundResults;

    public void veiwTrainSchedule(String depart, String destination, String date) {
        sendKeys(fromStationField, depart);
        sendKeys(toStationField, destination);
        sendKeys(dateDepartmentField, date);
        click(submitButton);
    }

    public String getRouteFoundText() {
        return read(routeTextFound);
    }

    public String getDateFoundText() {
        return read(dateTextFound);
    }

    //TODO: del if not used later
    public WebTable getTableFoundResults() {
        return new WebTable(getElement(tableResultTrains));
    }


}
