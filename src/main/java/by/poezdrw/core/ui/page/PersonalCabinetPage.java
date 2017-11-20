package by.poezdrw.core.ui.page;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
//========================DONE=====================//
public class PersonalCabinetPage extends HomePage{

    @FindBy(xpath = "//a[contains(@href,'profile')]")
    private WebElement registrationDataButton;

    public PersonalDataUpdatePage goToPersonalDataUpdatePage() {
//        LOG.info("click: 'Registration Data'");
        click(registrationDataButton);
        return new PersonalDataUpdatePage();
    }



}
