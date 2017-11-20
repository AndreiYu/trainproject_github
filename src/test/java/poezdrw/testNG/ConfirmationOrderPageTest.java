package poezdrw.testNG;

import by.poezdrw.core.business_object.TrainOrder;
import by.poezdrw.core.business_object.User;
import by.poezdrw.core.ui.page.AbstractPage;
import by.poezdrw.core.ui.page.ConfirmationOrderPage;
import by.poezdrw.core.ui.page.HomePage;
import org.testng.Assert;
import org.testng.annotations.*;
import poezdrw.testNG.testbase.AbstractTest;

public class ConfirmationOrderPageTest extends AbstractTest {

    private static AbstractPage testStartPage;

    private User testUser;
    private TrainOrder testTrainOrder;


    @BeforeMethod
    public void initPage() {

        testUser = TestDataProvider.getValidUserForBooking();
        testTrainOrder = TestDataProvider.getValidOrderForBooking();

        ConfirmationOrderPage confirmationOrderPage = new HomePage().openHomepage()
                .clickButtonLogin()
                .login(testUser.getLogin(), testUser.getPassword())
                .enterFromToRoute(testTrainOrder.getDeparturePoint(), testTrainOrder.getDestinationPoint())
                .enterDepartureDate(testTrainOrder.getStringDepartureDate()) //can be changed to another date provided later, when TestDataProvider.class will be done to accept .csv data
                .enterAdultPassengersNumber(TestDataProvider.NUMBER_ADULT_PASSENGERS)
                .continueToTrainChoice()
                .chooseAnyTrain() //can be changed later to choose special train provided in test data
                .chooseAnyCarriage()
                .selectPassengerFromPreviouslyCompletedOrder()
                .chooseAnyRandomPlace()
                .clickAcceptTermsAndConditionsCheckbox()
                .continueToPayment();
        testStartPage = confirmationOrderPage;
    }

    @Test
    public void paymentMethodInternetBankingTest() {
        ConfirmationOrderPage confirmationOrderPage = (ConfirmationOrderPage) testStartPage;
        confirmationOrderPage.choicePaymentMethod(TestDataProvider.PaymentType.IB.ordinal());
        Assert.assertTrue(confirmationOrderPage.isContinuePaymentInternetBankingOpen()
                .contains(TestDataProvider.INTERNET_BANKING_CHECK_TEXT));
    }

    @Test
    public void paymentMethodEripTest() {
        ConfirmationOrderPage confirmationOrderPage = (ConfirmationOrderPage) testStartPage;
        confirmationOrderPage.choicePaymentMethod(TestDataProvider.PaymentType.ERIP.ordinal());
        Assert.assertTrue(confirmationOrderPage.isNewWindowAfterPaymentRaschet()
                .contains(TestDataProvider.ERIP_CHECK_TEXT));
    }

    @Test
    public void paymentMethodBankCard() {
        ConfirmationOrderPage confirmationOrderPage = (ConfirmationOrderPage) testStartPage;
        confirmationOrderPage.choicePaymentMethod(TestDataProvider.PaymentType.BANK_CARD.ordinal());
        Assert.assertTrue(confirmationOrderPage.isContinuePaymentBankCard()
                .equalsIgnoreCase(TestDataProvider.CARD_CHECK_TEXT));
    }

    @AfterClass()
    public void tearDown() {
        testStartPage = null;

        testUser = null;
        testTrainOrder = null;
    }
}
