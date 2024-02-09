import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ProfileTests extends BaseTest{
    @Test
    public void changeUserNameTest() throws InterruptedException {
        //Login into Webapp
        openLoginUrl();
        enterEmail("Verify12@gmail.com");
        enterPassword("te$t$tudent");
        clickSubmit();
        Thread.sleep(2000);
        clickOnAvatar();
        providePassword();
        String newUsername ="Ria";
        enterNewUserName(newUsername);
        clickOnSave();
        Thread.sleep(2000);

        WebElement avatarName=driver.findElement(By.cssSelector(".view-profile .name"));
        Assert.assertEquals(avatarName.getText(),newUsername);


    }


}
