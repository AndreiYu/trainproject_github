package poezdrw.cucumber.stepdef;

import by.poezdrw.core.ui.page.HomePage;
import by.poezdrw.core.ui.page.InfoAboutPlacesAvailableAndCostPage;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.testng.Assert;
import poezdrw.cucumber.AbstractCucumberTest;

public class SwapStationStepDef extends AbstractCucumberTest {

    @And("^User navigates to the Info About Places Available And Cost Page$")
    public void userNavigatesToTheInfoAboutPlacesAvailableAndCostPage() {

        page = ((HomePage)page).goToTicketBookingPage();
    }

    @When("^User enters  the departure point \"([^\"]*)\" and destination point \"([^\"]*)\" and presses swap-button$")
    public void userEntersTheDeparturePointAndDestinationPointAndPressesSwapButton(String point_from, String point_to) {

        ((InfoAboutPlacesAvailableAndCostPage) page)
                .enterFromToRoute(point_from, point_to)
                .clickSwapStationsButton();
    }

    @Then("^User sees that departure point became \"([^\"]*)\" and destination point became \"([^\"]*)\"$")
    public void userSeesThatDeparturePointBecameAndDestinationPointBecame(String arg0, String arg1) {

        Assert.assertTrue(((InfoAboutPlacesAvailableAndCostPage) page).getDepartStationEntered().equals(arg0) &&
                ((InfoAboutPlacesAvailableAndCostPage) page).getArrivalStationEntered().equals(arg1));
    }

}

