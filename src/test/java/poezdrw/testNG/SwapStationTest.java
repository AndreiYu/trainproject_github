package poezdrw.testNG;

import by.poezdrw.core.business_object.TrainOrder;
import by.poezdrw.core.ui.page.AbstractPage;
import by.poezdrw.core.ui.page.HomePage;
import by.poezdrw.core.ui.page.InfoAboutPlacesAvailableAndCostPage;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;
import poezdrw.testNG.testbase.AbstractTest;

//========================DONE=====================//
public class SwapStationTest extends AbstractTest {

    private AbstractPage testStartPage;
    TrainOrder testTrainOrder;

    /**
     * Factory to run parametrisized tests with @BeforeClass
     * @param trainOrder
     */
    @Factory(dataProvider = "ValidTrainOrders", dataProviderClass = TestDataProvider.class)
    public SwapStationTest(TrainOrder trainOrder) {

        this.testTrainOrder = trainOrder;
    }

    @BeforeClass()
    public void setUp() {
        InfoAboutPlacesAvailableAndCostPage infoAboutPlacesAvailableAndCostPage = new HomePage().openHomepage()
                .goToTicketBookingPage();
        testStartPage = infoAboutPlacesAvailableAndCostPage;
    }

    @Test(description = "Positive test for swapping FROM-TO stations while searching for available train")
    public void swapFromToStationTest() {

        InfoAboutPlacesAvailableAndCostPage page = (InfoAboutPlacesAvailableAndCostPage) testStartPage;
        page.enterFromToRoute(testTrainOrder.getDeparturePoint(), testTrainOrder.getDestinationPoint());
        page.clickSwapStationsButton();
        Assert.assertTrue(page.getArrivalStationEntered().equals(testTrainOrder.getDeparturePoint()) &&
                page.getDepartStationEntered().equals(testTrainOrder.getDestinationPoint()));
    }
}
