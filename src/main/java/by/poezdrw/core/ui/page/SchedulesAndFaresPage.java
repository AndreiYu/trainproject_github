package by.poezdrw.core.ui.page;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
//========================DONE=====================//
public class SchedulesAndFaresPage extends HomePage {


    @FindBy(xpath = "//span[contains(@id, '_532737dd')]")
    private WebElement buttonAboutTrain;

    @FindBy(xpath = "//span[contains(@id, '_53273763')]")
    private WebElement buttonAboutStation;

    @FindBy(xpath = "//span[contains(@id, '_53273731')]")
    private WebElement buttonTrainSchedule;

    @FindBy(xpath = "//span[contains(@id, '_532736c7')]")
    private WebElement buttonPlacesAvailableAndCost;

    public InfoAboutTrainPage goToInfoAboutTrainPage() {
        return (InfoAboutTrainPage) definePage(buttonAboutTrain);
    }

    public InfoAboutStationPage goToInfoAboutStationPage() {
        return (InfoAboutStationPage) definePage(buttonAboutStation);
    }

    public InfoAboutPlacesAvailableAndCostPage goToInfoAboutPlacesAvailableAndCostPage() {
        return (InfoAboutPlacesAvailableAndCostPage) definePage(buttonPlacesAvailableAndCost);
    }

    public InfoAboutTrainSchedulePage goToInfoAboutTrainSchedulePage() {
        return (InfoAboutTrainSchedulePage) definePage(buttonTrainSchedule);
    }

    private SchedulesAndFaresPage definePage(WebElement element) {
//        LOG.info("click: 'Train Schedule'");
        SchedulesAndFaresPage resultPage = element.equals(buttonAboutTrain) ? (resultPage = new InfoAboutTrainPage()) :
        element.equals(buttonAboutStation) ? (resultPage = new InfoAboutStationPage()) :
        element.equals(buttonPlacesAvailableAndCost) ? (resultPage = new InfoAboutPlacesAvailableAndCostPage()) :
        (resultPage = new InfoAboutTrainSchedulePage());

        click(element);
        return resultPage;
    }

}
