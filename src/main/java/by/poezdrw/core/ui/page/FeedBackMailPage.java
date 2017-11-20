package by.poezdrw.core.ui.page;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
//========================DONE=====================//
public class FeedBackMailPage extends HomePage {

    @FindBy(id = "FeedbackMailPortletSubjectId")
    private WebElement feedBackDropBox;

    @FindBy(id = "FeedbackMailPortletMessage")
    private WebElement feedBackTextArea;

    @FindBy(xpath = "//input[@id='captcha' and @class='inputText error']")
    private WebElement captchaInputAreaError;

    @FindBy(id = "FeedbackMailPortletFio")
    private WebElement feedBackFio;

    @FindBy(id = "FeedbackMailPortletEmail")
    private WebElement feedBackEmail;

    @FindBy(id = "captcha")
    private WebElement captchaInputArea;

    @FindBy(xpath = "//*[contains(@class,'error') and not (@class='error')]")
    private List<WebElement> errorEmptyInput;

    @FindBy(xpath = "//input[@class='commandExButton']")
    private WebElement sendButton;

    //all dropdown menu list
    @FindBy(xpath = "//select/option")
    private List<WebElement> allOptionInSelectDropBox;

    public void selectSubject(String text) {
        select(feedBackDropBox, text);
    }

    public FeedBackMailPage selectLastAvailableSubject() {
        int listSize = allOptionInSelectDropBox.size();
        if (listSize > 0) {
            WebElement lastElement = allOptionInSelectDropBox.get(listSize - 1);
            click(lastElement);//we pass option, so just click instead of using select()
        }
        return new FeedBackMailPage();
    }

    public FeedBackMailPage typeTextToTextArea(String text) {
        sendKeys(feedBackTextArea, text);
        return new FeedBackMailPage();
    }

    public FeedBackMailPage confirmEnteredData() {
        click(sendButton);
        return  new FeedBackMailPage();
    }


    //Methods for assertion
    public boolean captchaErrorIsDisplayed() {
        return isElementPresent(captchaInputAreaError);
    }

    public boolean feedBackFioErrorIsDisplayed() {
        return isElementPresent(feedBackFio);
    }

    public boolean feedBackEmailErrorIsDisplayed() {
        return isElementPresent(feedBackEmail);
    }

    public boolean feedBackTextAreaErrorIsDisplayed() {
        return isElementPresent(feedBackTextArea);
    }

    //Methods for getting text from Input Form_del

    public String getFeedBackFio() {
        return getAttribute(feedBackFio, "value");
    }

    public String getFeedBackEmail() {
        return getAttribute(feedBackEmail, "value");
    }

    public String getFeedBackTextArea() {
        return getAttribute(feedBackTextArea, "value");
    }

}
