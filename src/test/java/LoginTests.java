import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;

public class LoginTests extends BaseTest {

    //@Test(enabled=false, description = "Test skipped because of open issue JIRA-123",priority =1)

    @Test(dataProvider = "CorrectLoginProviders",dataProviderClass = BaseTest.class)
    public void LoginWithValidEmailPasswordTest(String email,String password) {
        //Create object of page classes to access elements and methods
        LoginPage loginPage = new LoginPage(driver);
        HomePage homePage = new HomePage(driver);
        loginPage.provideEmail(email);
        loginPage.providePassword(password);
        loginPage.clickLogin();
        //check avatar for Homepage
        Assert.assertTrue(homePage.getUserAvatar().isDisplayed());
        }

    @Test(enabled = true, dataProvider = "IncorrectLoginProviders",dataProviderClass = BaseTest.class)
         public void LoginWithInvalidEmailPassword(String email,String password) {
        LoginPage loginPage = new LoginPage(driver);
        //Enter Email
        loginPage.provideEmail(email);
        //Enter password
        loginPage.providePassword(password);
        //Enter submit
        loginPage.clickLogin();
        //check for Login button and url
             Assert.assertEquals(driver.getCurrentUrl(), url);
             Assert.assertTrue(loginPage.getLoginBtn().isDisplayed());
         }
}




