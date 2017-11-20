package by.poezdrw.core.ui.page;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
//========================DONE=====================//
public class PersonalDataUpdatePage extends AbstractPage {

    @FindBy(xpath = "//input[contains(@id,'_userLogin')]")
    private WebElement userLoginField;

    @FindBy(xpath = "//input[contains(@id,'_pwd')]")
    private WebElement userPasswordField;

    @FindBy(id = "appPassword")
    private WebElement submitPasswordField;

    @FindBy(id = "lastname")
    private WebElement lastNameField;

    @FindBy(id = "firstname")
    private WebElement firstNameField;

    @FindBy(id = "patronymic")
    private WebElement middleNameField;

    @FindBy(id = "phone")
    private WebElement phoneField;

    @FindBy(id = "country")
    private WebElement countryField;

    @FindBy(id = "address")
    private WebElement addressField;

    @FindBy(id = "email")
    private WebElement emailField;

    @FindBy(id = "sex")
    private WebElement genderField;

    @FindBy(id = "age")
    private WebElement ageField;

    @FindBy(id = "save")
    private WebElement saveButton;

    @FindBy(css = "#message>b>font")
    private WebElement successText;

    @FindBy(id = "logoutlink")
    private WebElement signoutButton;

    public void changeLastName(String newLastName) {
        sendKeys(lastNameField, newLastName);
        click(saveButton);
    }

    public String getLastNameDisplayed() {
        return getAttribute(lastNameField, "value");
    }


}
