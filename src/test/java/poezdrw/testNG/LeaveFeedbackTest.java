package poezdrw.testNG;

import by.poezdrw.core.business_object.User;
import by.poezdrw.core.ui.page.AbstractPage;
import by.poezdrw.core.ui.page.FeedBackMailPage;
import by.poezdrw.core.ui.page.HomePage;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import poezdrw.testNG.testbase.AbstractTest;

//========================DONE=====================//
public class LeaveFeedbackTest extends AbstractTest {

    private AbstractPage testStartPage;

    private User testUser;

    //TODO: check if driver instance is single for all pages in one thread. Overwise make it static Threadlocal
    @BeforeClass()
    public void setUp() {

        testUser = TestDataProvider.getValidUserForBooking();

        FeedBackMailPage feedBackPage = new HomePage().openHomepage() //HomePage
                .clickButtonLogin() //LoginPage
                .login(testUser.getLogin(), testUser.getPassword()) //TicketBookingPage_del
                .goToFeedBackMailPage(); //FeedBackMailPage
        testStartPage = feedBackPage;
    }

    @Test(description = "Leave feedback form negative test: leave feedback as logged in " +
            "user and check if captcha error message is displayed and other obligatory fields are not empty")
    public void leaveFeedBackTest() {

        FeedBackMailPage feedBackPage = (FeedBackMailPage) testStartPage;
        feedBackPage.selectLastAvailableSubject();
        feedBackPage.typeTextToTextArea(TestDataProvider.FEEDBACK_TEXT);
        feedBackPage.confirmEnteredData();

        Assert.assertTrue(feedBackPage.captchaErrorIsDisplayed() &&
                !feedBackPage.getFeedBackFio().isEmpty() &&
                !feedBackPage.getFeedBackEmail().isEmpty() &&
                !feedBackPage.getFeedBackTextArea().isEmpty());
    }



}
