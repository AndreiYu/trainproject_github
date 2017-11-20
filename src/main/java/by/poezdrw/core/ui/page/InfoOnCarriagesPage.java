package by.poezdrw.core.ui.page;

import by.poezdrw.exception.WrongElementException;
import by.poezdrw.helper.Randomiser;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;


//TODO refactor
public class InfoOnCarriagesPage  extends SchedulesAndFaresPage{


    @FindBy(xpath = "//table//span[contains(text(),'СВ')]")
    private WebElement tableWithWagonsOfTypeSV;

    @FindBy(xpath = "//table//span[contains(text(),'Купейный')]")
    private WebElement tableWithWagonsOfTypeCompartment;

    @FindBy(xpath = "//table//span[contains(text(),'Плацкартный')]")
    private WebElement tableWithCarriagesOfTypePlackart;

    @FindBy(xpath = "table//span[contains(text(),'Общий')]")
    private WebElement tableWithWagonsTypeOverall;
// + "с местами для сидения" и др виды.

    @FindBy(xpath = "//a//span[contains(@id,'tableEx4')]")
    private WebElement firstFoundNumberCarriageTrain;

    @FindBy(xpath = "//a//span[contains(@id,'tableEx4')]") // list of all wagon numbers available for booking for found trains
    private List<WebElement> allNumbersCarriageTrain;

    @FindBy(xpath = "//input[contains(@onclick,'btSetSelWagon')]") // all ChooseCarriage buttons/ Bottons contain CarriageNumber in @onclick attribute
    private List<WebElement> allChooseCarriageButtons;

    @FindBy(xpath = "//span[contains(text(),'Информация по вагонам')]")
    private WebElement checkInformationPageValid;


    public DataOnPassengersPage chooseAnyCarriage() {
        if (isElementPresent(firstFoundNumberCarriageTrain)) {
            String carriageNumber = read(Randomiser.getRandomWebElementValue(allNumbersCarriageTrain));
            return chooseButtonDependingOnCarriageNumber(carriageNumber);
        }
        else {
            throw new WrongElementException("Carriages to choose from not found on " +
                    DataOnPassengersPage.class.getSimpleName() + " page");
        }
    }

    public DataOnPassengersPage chooseButtonDependingOnCarriageNumber(String carriageNumber) {
        for (WebElement button: allChooseCarriageButtons) {
            if (button.getAttribute("onclick").contains("btSetSelWagon(\"" + carriageNumber + "\"")) {
                click(button);
                return new DataOnPassengersPage();
            }
        }
        throw new WrongElementException("Unable to find button to click according to carriageNumber provided");
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
