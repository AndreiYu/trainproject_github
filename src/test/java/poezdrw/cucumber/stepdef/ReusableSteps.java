package poezdrw.cucumber.stepdef;

import by.poezdrw.core.ui.page.HomePage;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import poezdrw.cucumber.AbstractCucumberTest;

public class ReusableSteps  extends AbstractCucumberTest {

    //make page = null again to allow other scenarios use it again from scratch
    @Before("@base")
    public void beforeScenario() {
        page = null;
    }

    @Given("^(?:\\w*) opens? HomePage$")
    public void iOpenHomePage() {
        page = new HomePage().openHomepage();
    }





}
