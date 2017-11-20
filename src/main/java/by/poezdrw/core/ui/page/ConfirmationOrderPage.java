package by.poezdrw.core.ui.page;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class ConfirmationOrderPage extends SchedulesAndFaresPage {

    @FindBy(xpath = "//span[contains(text(),'МИНСК-ПАССАЖИРСКИЙ')]")
    private WebElement departureStation;

    @FindBy(xpath = "//span[contains(text(),'БРЕСТ-ЦЕНТРАЛЬНЫЙ')]")
    private WebElement arrivalStation;

    @FindBy(xpath = "//input[contains(@id,'registrationNeeded') and @type='checkbox']")
    private WebElement checkboxElectronicRegistration;

    @FindBy(xpath = "//input[contains(@value,'Отменить заказ')]")
    private WebElement buttonCancelOrder;

    @FindBy(xpath = "//input[contains(@id,'confirm') and @class='commandExButton']")
    private WebElement buttonPayOrder;

    @FindBy(xpath = "//span[contains(@id,'form1')]") // name of all payment methods
    private List<WebElement> nameAllPaymentMethods;

    @FindBy(xpath = "//input[contains(@id,'form1') and @type='radio']") // all radio button
    private List<WebElement> allRadioButtonPaymentMethods;

    @FindBy(xpath = "//div[@id='tinycontent']//*[contains(text(),'Обращаем Ваше внимание!')]")
    private WebElement warningWindowWhenPayingRaschet;

    @FindBy(xpath = "//div[@id='tinybox']//*[@id='tinybox_close_btn']/img")
    private WebElement closePopup;  //всплывающее окно

    @FindBy(xpath = "//div//span[contains(text(),'Ваш код платежа в системе «Расчёт»')]")
    private WebElement checkLocatorPagePaymentMethodRaschet;

    @FindBy(xpath = "//div[@id='tinybox']//input[@value='Да']")
    private WebElement warningWindowClose;

    @FindBy(xpath = "//div[@id='footer']//p[contains(text(),'Беларусбанк')]")
    private WebElement internetBankingBelarusbankPage;

    @FindBy(xpath = "//div[@id='MainContent']/div[2]/span")
    private WebElement paymentByCreditCard;

    //TODO: rename method?
    public ConfirmationOrderPage choicePaymentMethod(int i) {
        if (isElementPresent(checkboxElectronicRegistration)) {
            click(checkboxElectronicRegistration);
        }
        switch (i) {
            case (0):
                click(allRadioButtonPaymentMethods.get(0));
                break;
            case (1):
                click(allRadioButtonPaymentMethods.get(1));
                if (isElementPresent(closePopup)) {
                    click(closePopup);
                }
                break;
            case (2):
                click(allRadioButtonPaymentMethods.get(2));
                break;
        }
        if (isElementPresent(warningWindowClose)) {
            click(warningWindowClose);
        }
        else {
            click(buttonPayOrder);
        }
        return this;
    }

    public String isNewWindowAfterPaymentRaschet() {
        return read(checkLocatorPagePaymentMethodRaschet);
    }

    public String isContinuePaymentInternetBankingOpen() {
        return read(internetBankingBelarusbankPage);
    }

    public String isContinuePaymentBankCard() {
        return read(paymentByCreditCard);
    }

}


