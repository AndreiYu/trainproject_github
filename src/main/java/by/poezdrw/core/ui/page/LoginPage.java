package by.poezdrw.core.ui.page;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
//========================DONE=====================//
public class LoginPage extends HomePage {

    @FindBy(xpath = "//input[@id='login']")
    private WebElement loginField;

    @FindBy(xpath = "//input[@id='password']")
    private WebElement passwordField;

    @FindBy(xpath = "//input[contains(@name, '_login')]")
    private WebElement loginButton;

    //TODO: this element can be used when logged in later. Move to abstractPage and make protected?
    @FindBy(xpath = "//div//input[@class='selectBooleanCheckbox']")
    private WebElement checkBoxFarmiliarizationRules;

    //TODO: change methodName to "processLogin"
    public InfoAboutPlacesAvailableAndCostPage login(String userName, String userPassword) {
//        LOG.info("Enter UserName and Password");
        sendKeys(loginField, userName);
        sendKeys(passwordField, userPassword);
        click(loginButton);
        if (isElementPresent(checkBoxFarmiliarizationRules)) {
            click(checkBoxFarmiliarizationRules);
        }
        return new InfoAboutPlacesAvailableAndCostPage();
    }


}
