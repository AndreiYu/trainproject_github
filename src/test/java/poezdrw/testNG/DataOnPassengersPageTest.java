package poezdrw.testNG;

import by.poezdrw.core.business_object.BaseBusinessObject;
import by.poezdrw.core.business_object.TrainOrder;
import by.poezdrw.core.business_object.User;
import by.poezdrw.core.ui.page.AbstractPage;
import by.poezdrw.core.ui.page.DataOnPassengersPage;
import by.poezdrw.core.ui.page.HomePage;

import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import poezdrw.testNG.testbase.AbstractTest;

import java.util.List;

public class DataOnPassengersPageTest extends AbstractTest {

    private AbstractPage testStartPage;

    private User testUser;
    private TrainOrder testTrainOrder;

    @BeforeClass
    public void setUp() {

        testUser = TestDataProvider.getValidUserForBooking();
        testTrainOrder = TestDataProvider.getValidOrderForBooking();

        DataOnPassengersPage dataOnPassengersPage = new HomePage().openHomepage()
                .clickButtonLogin()
                .login(testUser.getLogin(), testUser.getPassword())
                .enterFromToRoute(testTrainOrder.getDeparturePoint(), testTrainOrder.getDestinationPoint())
                .enterDepartureDate(testTrainOrder.getStringDepartureDate()) //can be changed to another date provided later, when TestDataProvider.class will be done to accept .csv data
                .enterAdultPassengersNumber(TestDataProvider.NUMBER_ADULT_PASSENGERS)
                .continueToTrainChoice()
                .chooseAnyTrain() //can be changed later to choose special train provided in test data
                .chooseAnyCarriage();
        testStartPage = dataOnPassengersPage;

    }

    @Test(description = "")
    public void isDataPassengersFilling() {
        DataOnPassengersPage dataOnPassengersPage = (DataOnPassengersPage) testStartPage;
        dataOnPassengersPage.fillSinglePassengerData(testUser.getLastName(), testUser.getFirstName(), testUser.getPassport());

        Assert.assertTrue(dataOnPassengersPage.getFirstNamePassenger().equals(testUser.getFirstName())
                & dataOnPassengersPage.getLastNamePassenger().equals(testUser.getLastName())
                & dataOnPassengersPage.getDocNumber().equals(testUser.getPassport()));
    }

    @Test(description = "")
    public void isDataPassengersFillingPreviouslyCompletedOrder() {
        List<BaseBusinessObject> previouslyEnteredPassengersList =  TestDataProvider
                .getValidBusinessObjectTestData(this.getClass().getSimpleName(), TestDataProvider.BusinessObjectType.USER);

        DataOnPassengersPage dataOnPassengersPage = (DataOnPassengersPage) testStartPage;
        dataOnPassengersPage.selectPassengerFromPreviouslyCompletedOrder();
        User newUser = new User(dataOnPassengersPage.getLastNamePassenger(),
                dataOnPassengersPage.getFirstNamePassenger(),
                dataOnPassengersPage.getDocNumber());

        Assert.assertTrue(previouslyEnteredPassengersList.contains(newUser));//TODO: change to checking with loaded from file ArrayList
    }

    @AfterClass()
    public void tearDown() {
        testStartPage = null;

        testUser = null;
        testTrainOrder = null;
    }

}
