package poezdrw.testNG;

import by.poezdrw.core.business_object.TrainOrder;
import by.poezdrw.core.ui.page.*;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;
import poezdrw.testNG.testbase.AbstractTest;
//========================DONE=====================//
public class SearchTrainByNumberTest extends AbstractTest {

    private AbstractPage testStartPage;
    private TrainOrder testTrainOrder;

    /**
     * Factory to run parametrisized tests with @BeforeClass
     * @param trainOrder
     */
    @Factory(dataProvider = "ValidTrainOrders", dataProviderClass = TestDataProvider.class)
    public SearchTrainByNumberTest(TrainOrder trainOrder) {

        this.testTrainOrder = trainOrder;
    }

    @BeforeClass()
    public void setUp() {
        InfoAboutTrainPage infoAboutStationPage = new HomePage().openHomepage()
                .goToTicketBookingPage()
                .goToInfoAboutTrainPage();
        testStartPage = infoAboutStationPage;
    }

    @Test(description = "Positive test to get info about train on any date by it's partial number by direct input")
    public void findTrainByNumberAndAnyDateByInputTest() {

        InfoAboutTrainPage page = (InfoAboutTrainPage) testStartPage;
        page.findTrainByNumberAndAnyDateByInput(testTrainOrder.getTrainNumber());

        Assert.assertTrue(page.getTrainNumberAfterSearchDisplayed()
                .contains(testTrainOrder.getTrainNumber()));
    }


}
