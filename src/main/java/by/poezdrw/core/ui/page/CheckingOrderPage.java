package by.poezdrw.core.ui.page;

import by.poezdrw.core.ui.element.WebTable;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CheckingOrderPage extends SchedulesAndFaresPage {

    @FindBy(xpath = "//input[contains(@id,'_4f5b6c2c')]")
    private WebElement toThePreviousPageButton;

    @FindBy(xpath = "//input[contains(@id,'_4f5b6c32')]")
    private WebElement toTheBeginingButton;

    @FindBy(xpath = "//input[@type='submit' and contains(@onclick,'btSetOverlayStyle')]")
    private WebElement buttonContinue;

    @FindBy(xpath = "//tbody/tr[2]/td/span[contains(text(),'')]")
    private WebElement stationDeparture;

    @FindBy(xpath = "//tbody/tr[3]/td/span[contains(text(),'')]")  // station arrival
    private WebElement stationArrival;

    @FindBy(xpath = "//span[contains(@style,'bold')]/span[1]") // date departure
    private WebElement dateDeparture;

    @FindBy(xpath = "//div//tbody//td[3]/span[1]")
    private WebElement nameAndLastnamePassenger;

    @FindBy(xpath = "//span[contains(text(),'Выберите маршрут следования')]")
    private WebElement checkInformationBeginningPage;

    @FindBy(xpath = "//span[contains(text(),'Укажите данные о пассажирах')]")
    private WebElement checkInformationPreviousPage;

    @FindBy(xpath = "//span[contains(text(),'Паспорт Республики Беларусь')]")
    private WebElement passportNumberCheckOrderPage;

    @FindBy(xpath = "//tbody//tr[8]/td//span[contains(text(),'')]") // number carriage
    private WebElement fieldNumberCarriage;

    @FindBy(xpath = "//td[5]/p[1]//span[contains(text(),'')]")
    private List<WebElement> pathPreviousPageNumberCarriage;

    @FindBy(xpath = "//input[@type='text' and contains(@id,'textDepStat')]")
    private WebElement departmentStationField;

    @FindBy(xpath = "//input[@type='text' and contains(@id,'textArrStat')]")
    private WebElement arrivalStationField;

    @FindBy(xpath = "//input[contains(@class,'datepicker')]")
    private WebElement dataDepartmentField;

    @FindBy(xpath = "//input[contains(@id,'lastname')]")   //lastname
    private WebElement lastnameField;

    @FindBy(xpath = "//input[contains(@id,':name')]") // name
    private WebElement nameField;

    @FindBy(xpath = "//input[contains(@id,'docNum')]")
    private WebElement docNumberField;  // passport Number

    @FindBy(xpath = "//table[@class=\"fields\"]")
    private WebElement trainDataSummary;

    @FindBy(xpath = "//table[@class='information']")
    private WebElement passengerDataSummary;

    /**
     * custom WebTable usage
     */
    private WebTable trainDataTable = new WebTable(trainDataSummary);

    private WebTable passengerDataTable = new WebTable(passengerDataSummary);


    public Map<String, String> getTrainDataDisplayed() {
        Map<String, String> resultMap = new HashMap<>();
        int row = trainDataTable.getRowCount();
        for (int i = 1; i <= row; i++) {
            resultMap.put(trainDataTable.getCellData(i, 1),
                    trainDataTable.getCellData(i, 2));
        }
        return  resultMap;
    }

    public List<String> getPassengerDataDisplayed() {
        List<String> resultList = new ArrayList<>();
        int row = passengerDataTable.getRowCount();
        for (int i = 2; i <= row; i++) {
            resultList.add(passengerDataTable.getCellData(i, 3));
        }
        return  resultList;
    }

    public ConfirmationOrderPage continueToPayment() {
        click(buttonContinue);
        return new ConfirmationOrderPage();
    }



}
