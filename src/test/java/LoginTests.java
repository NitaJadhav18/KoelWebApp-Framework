import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTests extends BaseTest {

    //@Test(enabled=false, description = "Test skipped because of open issue JIRA-123",priority =1)

    @Test
    public void LoginWithValidEmailPasswordTest() {
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

    @Test(dataProvider = "IncorrectLoginProviders",dataProviderClass = BaseTest.class)
         public void LoginWithInvalidEmailPassword(String email,String password) {
        //Enter Email
        enterEmail(email);
        //Enter Password
        enterPassword(password);
        //Click on Log In
        clickSubmit();
             WebElement loginBtn = driver.findElement(By.cssSelector("[type='submit']"));

             Assert.assertEquals(driver.getCurrentUrl(), url);
             Assert.assertTrue(loginBtn.isDisplayed());
         }
}




