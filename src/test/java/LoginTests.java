import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class LoginTests extends BaseTest {

    //@Test(enabled=false, description = "Test skipped because of open issue JIRA-123",priority =1)

    @Test
    public void LoginWithValidEmailPasswordTest() {
        //Open the Url
        openLoginUrl();
        //Enter Email
        enterEmail("Verify12@gmail.com");
        //Enter Password
        enterPassword("te$t$tudent");
        //Click on Log In
        clickSubmit();
        //check avatar for Homepage
        WebElement avatarField = driver.findElement(By.cssSelector("a>.avatar"));
        Assert.assertTrue(avatarField.isDisplayed());
        }

    @Test
         public void LoginWithEmptyEmailPassword() {
             //Open the Url
             openLoginUrl();
             //Click on Log In
             clickSubmit();
             WebElement loginBtn = driver.findElement(By.cssSelector("[type='submit']"));

             Assert.assertEquals(driver.getCurrentUrl(), url);
             Assert.assertTrue(loginBtn.isDisplayed());
         }
}




