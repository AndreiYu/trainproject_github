package poezdrw.testNG;

import by.poezdrw.core.business_object.TrainOrder;
import by.poezdrw.core.business_object.User;
import by.poezdrw.core.ui.page.AbstractPage;
import by.poezdrw.core.ui.page.CheckingOrderPage;
import by.poezdrw.core.ui.page.DataOnPassengersPage;
import by.poezdrw.core.ui.page.HomePage;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;
import poezdrw.testNG.testbase.AbstractTest;

import java.util.List;
import java.util.Map;
import java.util.function.Predicate;


public class CheckingOrderPageTest extends AbstractTest {

    private AbstractPage testStartPage;

    private User testUser;
    private TrainOrder testTrainOrder;

    /**
     * Factory to run parametrisized tests with @BeforeClass
     * @param trainOrder
     */
    @Factory(dataProvider = "ValidTrainOrders", dataProviderClass = TestDataProvider.class)
    public CheckingOrderPageTest(TrainOrder trainOrder) {

        this.testTrainOrder = trainOrder;
        this.testUser = TestDataProvider.getValidUserForBooking();
    }

    @BeforeClass(description = "Train number, train carriage and seatPlace are randomly selected")
    public void setUp() {

        AbstractPage checkingOrderPage = new HomePage().openHomepage()
                .clickButtonLogin()
                .login(testUser.getLogin(), testUser.getPassword())
                .enterFromToRoute(testTrainOrder.getDeparturePoint(), testTrainOrder.getDestinationPoint())
                .enterDepartureDate(testTrainOrder.getStringDepartureDate()) //can be changed to another date provided later, when TestDataProvider.class will be done to accept .csv data
                .enterAdultPassengersNumber(TestDataProvider.NUMBER_ADULT_PASSENGERS)
                .continueToTrainChoice()
                .chooseAnyTrain() //can be changed later to choose special train provided in test data
                .chooseAnyCarriage() //can be changed later to choose special train provided in test data
                .fillSinglePassengerData(testUser.getLastName(), testUser.getFirstName(), testUser.getPassport())
                .chooseAnyRandomPlace();

        String trainNumberRandomlySelected = ((DataOnPassengersPage) checkingOrderPage).getTrainNumber();
                testTrainOrder.setTrainNumber(trainNumberRandomlySelected);
        String carriageNumberRandomlySelected = ((DataOnPassengersPage) checkingOrderPage).getCarriageNumber();
                testTrainOrder.setCarriageNumber(carriageNumberRandomlySelected);
        String placeNumberRandomlySelected = ((DataOnPassengersPage) checkingOrderPage).getAndRemovePlaceNumberChosenByClick();
                testTrainOrder.setPlaceNumber(placeNumberRandomlySelected);

        checkingOrderPage = ((DataOnPassengersPage) checkingOrderPage)
                .clickAcceptTermsAndConditionsCheckbox();

        testStartPage = checkingOrderPage;
    }

    @Test(description = "Positive test to verify if all passenger data previously entered " +
            "is present on OrderPage")
    public void passengerDataProperlyShownTest() {
        CheckingOrderPage page = (CheckingOrderPage) testStartPage;

        List<String> passengerDataReturned = page.getPassengerDataDisplayed();

        //if several users will be later in ticket - we can rewrite predicate here//
        Predicate<String> predicate = e -> e.contains(testUser.getFirstName().toUpperCase()) &&
                e.contains(testUser.getLastName().toUpperCase()) &&
                e.contains(testUser.getPassport().toUpperCase());

        Assert.assertTrue(passengerDataReturned.stream().anyMatch(predicate));
    }

    @Test(description = "Positive test to verify if all trainOrder data previously chosen " +
            "is present on OrderPage")
    public void trainOrderDataProperlyShownTest() {

        CheckingOrderPage page = (CheckingOrderPage) testStartPage;

        Map<String, String> trainDataReturned = page.getTrainDataDisplayed();

        Assert.assertTrue(checkTrainOrderDataIsValid(trainDataReturned, testTrainOrder));
    }

    private boolean checkTrainOrderDataIsValid(Map<String, String> dataMap, TrainOrder trainOrder) {

        return
                dataMap.get(TestDataProvider.TRAIN_STRING_RU).contains(trainOrder.getTrainNumber()) &&
                        dataMap.get(TestDataProvider.FROM_STRING_RU).contains(trainOrder.getDeparturePoint()) &&
                        dataMap.get(TestDataProvider.TO_STRING_RU).contains(trainOrder.getDestinationPoint()) &&
                        dataMap.get(TestDataProvider.DATE_STRING_RU).contains(trainOrder.getStringDepartureDate()) &&
                        dataMap.get(TestDataProvider.CARRIAGE_STRING_RU).concat(" " + dataMap.get(TestDataProvider.CARRIAGE_TYPE_STRING_RU))
                                .contains(trainOrder.getCarriageNumber()) &&
                        dataMap.get(TestDataProvider.PLACE_STRING_RU).contains(trainOrder.getPlaceNumber());
    }

    @AfterClass()
    public void tearDown() {
        testStartPage = null;

        testUser = null;
        testTrainOrder = null;
    }

}



