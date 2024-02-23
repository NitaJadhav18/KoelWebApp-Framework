import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;
import pages.ProfilePreferencesPage;

import java.time.Duration;

import static java.util.concurrent.TimeUnit.SECONDS;

public class ProfileTests extends BaseTest{
    @Test( enabled = true)
    public void changeUserNameTest() throws InterruptedException {
        LoginPage loginPage = new LoginPage(driver);
        HomePage homePage =new HomePage(driver);
        ProfilePreferencesPage profilePreferencesPage = new ProfilePreferencesPage(driver);
        String newUsername ="Zia";
        loginPage.login();
        homePage.clickOnAvatar();
        profilePreferencesPage.providePassword("te$t$tudent");
        profilePreferencesPage.enterNewUserName(newUsername);
        profilePreferencesPage.clickOnSave();
        driver.navigate().refresh();
        Assert.assertEquals(homePage.getAvatarName(),newUsername); //check avatar name
    }
}
