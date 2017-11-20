package poezdrw.testNG;

import by.poezdrw.core.business_object.User;
import by.poezdrw.core.ui.page.*;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import poezdrw.testNG.testbase.AbstractTest;
//========================DONE=====================//
public class PersonalDataUpdateTest extends AbstractTest {

    private AbstractPage testStartPage;

    private User testUser;

    @BeforeClass()
    public void setUp() {

        testUser = TestDataProvider.getValidUserForBooking();

        PersonalDataUpdatePage personalDataUpdatePage = new HomePage().openHomepage() //HomePage
                .clickButtonLogin() //LoginPage
                .login(testUser.getLogin(), testUser.getPassword()) //InfoAboutPlacesAvailableAndCostPage
                .goToPersonalCabinetPage() //PersonalCabinetPage
                .goToPersonalDataUpdatePage(); //PersonalDataUpdatePage
        testStartPage = personalDataUpdatePage;
    }

    @Test(description = "Change personal data positive test: check if Last name is changed successfully")
    public void updateUserLastNameTest() {
        PersonalDataUpdatePage personalPage = (PersonalDataUpdatePage)testStartPage;
        personalPage.changeLastName(TestDataProvider.NEWLASTNAME);
        Assert.assertTrue(personalPage.getLastNameDisplayed()
                .equals(TestDataProvider.NEWLASTNAME));
    }

}
