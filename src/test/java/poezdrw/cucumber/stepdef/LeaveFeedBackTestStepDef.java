package poezdrw.cucumber.stepdef;

import by.poezdrw.core.ui.page.*;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.testng.Assert;
import poezdrw.cucumber.AbstractCucumberTest;
import poezdrw.testNG.TestDataProvider;

public class LeaveFeedBackTestStepDef extends AbstractCucumberTest{


    @Given("^(?:\\w*) navigates? to Login page$")
    public void iNavigateToLoginPage()  {
        page = ((HomePage)page).clickButtonLogin();
    }

    @Given("^(?:\\w*) logins? as (?:valid) user by (?:entering|typing) \"([^\"]*)\" and password \"([^\"]*)\"$")
    public void iLoginAsValidUserByEnteringAndPassword(String arg1, String arg2)  {
        page = ((LoginPage)page).login(arg1,arg2);
    }

    @Given("^(?:\\w*) (?:go|goes) to Leave feedback page$")
    public void iGoToLeaveFeedbackPage()  {
        page = ((InfoAboutPlacesAvailableAndCostPage)page).goToFeedBackMailPage();
    }

    @Given("^(?:\\w*) fills? (?:all the obligatory fields) in the? form with valid data (?:except|besides) captcha$")
    public void iFillAllTheObligatoryFieldsInTheFormWithValidDataExceptCaptcha()  {
        page = ((FeedBackMailPage)page).selectLastAvailableSubject()
                .typeTextToTextArea(TestDataProvider.FEEDBACK_TEXT);
    }

    @When("^(?:\\w*) clicks? (?:(?:submit|send) button|button (?:submit|send))$")
    public void iClickSubmitButton()  {
        page = ((FeedBackMailPage)page).confirmEnteredData();
    }

    @Then("^(?:\\w*) sees? all? the? obligatory? fields not empty and captcha error message? displayed$")
    public void iSeeAllTheObligatoryFieldsNotEmptyAndCaptchaErrorMessageDisplayed()  {
        FeedBackMailPage testPage = (FeedBackMailPage)page;
        Assert.assertTrue((testPage.captchaErrorIsDisplayed() &&
                !testPage.getFeedBackFio().isEmpty() &&
                !testPage.getFeedBackEmail().isEmpty() &&
                !testPage.getFeedBackTextArea().isEmpty()
        ));
    }



}
