package poezdrw.testNG;

import by.poezdrw.core.business_object.TrainOrder;
import by.poezdrw.core.ui.page.AbstractPage;
import by.poezdrw.core.ui.page.HomePage;
import by.poezdrw.core.ui.page.InfoAboutTrainSchedulePage;
import org.testng.Assert;
import org.testng.annotations.*;
import poezdrw.testNG.testbase.AbstractTest;

//========================DONE=====================//
public class ViewTrainScheduleTest extends AbstractTest {

    private AbstractPage testStartPage;
    private TrainOrder trainOrder;

    @Factory(dataProvider = "ValidTrainOrders", dataProviderClass = TestDataProvider.class)
    public ViewTrainScheduleTest(TrainOrder trainOrder) {

        this.trainOrder = trainOrder;
    }

    @BeforeClass()
    public void setUp() {
        InfoAboutTrainSchedulePage infoAboutTrainSchedulePage = new HomePage().openHomepage()
                .goToTicketBookingPage()
                .goToInfoAboutTrainSchedulePage();
        testStartPage = infoAboutTrainSchedulePage;
    }

    @Test(description = "Positive test for searching for train according to FROM, TO and DATE parameters ")
    public void viewTrainSchedulePositiveTest() {
        InfoAboutTrainSchedulePage page = (InfoAboutTrainSchedulePage) testStartPage;
        page.veiwTrainSchedule(trainOrder.getDeparturePoint(), trainOrder.getDestinationPoint(), trainOrder.getStringDepartureDate());
        Assert.assertTrue(page.getRouteFoundText().contains(trainOrder.getDeparturePoint()) &&
                page.getRouteFoundText().contains(trainOrder.getDestinationPoint()) &&
                page.getDateFoundText().equals(trainOrder.getStringDepartureDate()));
    }
}
