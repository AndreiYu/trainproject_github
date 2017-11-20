package poezdrw.cucumber.stepdef;

import by.poezdrw.core.business_object.TrainOrder;
import by.poezdrw.core.ui.page.AbstractPage;
import by.poezdrw.core.ui.page.HomePage;
import by.poezdrw.core.ui.page.InfoAboutPlacesAvailableAndCostPage;
import by.poezdrw.core.ui.page.InfoAboutTrainPage;
import cucumber.api.Transform;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.testng.Assert;
import poezdrw.cucumber.AbstractCucumberTest;
import poezdrw.cucumber.CucumberTrainOrderTransformer;

public class SearchTrainByNumberStepDef extends AbstractCucumberTest {

    private static TrainOrder trainOrder;


    @Given("^(?:\\w*) navigates? to Ticket booking page$")
    public void iNavigateToTicketBookingPage() {
        page = ((HomePage) page).goToTicketBookingPage();
    }

    @Given("^(?:\\w*) navigates? to Information about train page$")
    public void iNavigateToInformationAboutTrainPage() {
        page = ((InfoAboutPlacesAvailableAndCostPage) page).goToInfoAboutTrainPage();
    }

    /*
    Using Transformer.class to get TrainOrder from String provided
     */
    @When("^(?:\\w*) types? train number as \"([^\"]*)\", selects? (?:\"on any date\") and presses? (?:\"get\") button$")
    public void ITypeTrainNumberSelectAnyDateAndPressButtonContinue (
            @Transform(CucumberTrainOrderTransformer.class) TrainOrder trainOrder ) {
        this.trainOrder = trainOrder;
        page = ((InfoAboutTrainPage) page)
                .findTrainByNumberAndAnyDateByInput(trainOrder.getTrainNumber());
    }

    @Then("^(?:\\w*) sees? train? route for train? number provided?$")
    public void iSeeAllTheObligatoryFieldsNotEmptyAndCaptchaErrorMessageDisplayed() {

        InfoAboutTrainPage testPage = (InfoAboutTrainPage) page;
        Assert.assertTrue((testPage.getTrainNumberAfterSearchDisplayed()
                .contains(this.trainOrder.getTrainNumber())));
    }

    @After
    public void tearDown() {
        this.trainOrder = null;
    }

}
