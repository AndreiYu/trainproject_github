package by.poezdrw.core.ui.page;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
//========================DONE=====================//
public class InfoAboutTrainPage extends SchedulesAndFaresPage{

    @FindBy(xpath = "//input[contains(@id, 'form1:textStat')]")
    private WebElement trainNumber;

    @FindBy(xpath = "//label[contains(@for, 'form1:noDate:0')]")
    private WebElement anyDataButton;

    @FindBy(xpath = "//input[contains(@id, 'buttonSearch')]")
    private WebElement startSearch;

    @FindBy(xpath = "//a[contains(@id,'ui-id')]")
    private List<WebElement> selectedTrainList;

    @FindBy(xpath = "//span[contains(@id, '_77c47e25')]")
    private WebElement foundResult;
    // !!!TODO: in test check (by Select and click) if list selectedTrainList contains test number + check if foundResult.gettext contains test number

    //find train by direct input of full train number
    public InfoAboutTrainPage findTrainByNumberAndAnyDateByInput(String trainNumberSearched) {
        sendKeys(trainNumber, trainNumberSearched);
        click(anyDataButton);
        click(startSearch);
        return this;
    }

    //find train by input of partinal train number and selecting from dropdown
    //TODO: fix. don't work properly
    public InfoAboutTrainPage findTrainByNumberAndAnyDateBySelectAndClick(String trainNumberSearched) {
        sendKeys(trainNumber, trainNumberSearched);
        click(selectedTrainList
                .stream()
                .filter(a -> a.getText().contains(trainNumberSearched))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("Train " + trainNumberSearched + "not found and can't be clicked!")));
        click(anyDataButton);
        click(startSearch);
        return this;
    }

    public String getTrainNumberAfterSearchDisplayed() {
        return  read(foundResult);
    }



}
