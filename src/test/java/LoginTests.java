import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTests extends BaseTest {

    //@Test(enabled=false, description = "Test skipped because of open issue JIRA-123",priority =1)

    @Test(enabled = false, dataProvider = "CorrectLoginProviders",dataProviderClass = BaseTest.class)
    public void LoginWithValidEmailPasswordTest(String email,String password) {
        //Enter Email
        enterEmail(email);
        //Enter password
        enterPassword(password);
        //Enter submit
        clickSubmit();
        //check avatar for Homepage
        WebElement avatarField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("a>.avatar")));
        Assert.assertTrue(avatarField.isDisplayed());
        }

    @Test(enabled = false, dataProvider = "IncorrectLoginProviders",dataProviderClass = BaseTest.class)
         public void LoginWithInvalidEmailPassword(String email,String password) {
        //Enter Email
        enterEmail(email);
        //Enter password
        enterPassword(password);
        //Enter submit
        clickSubmit();
        //check for Login button and url
        WebElement loginBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[type='submit']")));
             Assert.assertEquals(driver.getCurrentUrl(), url);
             Assert.assertTrue(loginBtn.isDisplayed());
         }
}




