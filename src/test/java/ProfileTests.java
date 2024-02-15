import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ProfileTests extends BaseTest{
    @Test( enabled = false, dataProvider = "CorrectLoginProviders", dataProviderClass = BaseTest.class)
    public void changeUserNameTest(String email, String password) {
        login(email,password);
        clickOnAvatar();
        providePassword(password);
        String newUsername ="Ria";
        enterNewUserName(newUsername);
        clickOnSave();
        WebElement avatarName=wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".view-profile .name")));
        Assert.assertEquals(avatarName.getText(),newUsername); //check avatar name
    }
}
