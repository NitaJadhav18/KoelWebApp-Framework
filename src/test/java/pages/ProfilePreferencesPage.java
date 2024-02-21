package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ProfilePreferencesPage extends BasePage{
    public ProfilePreferencesPage(WebDriver givenDriver) {
        super(givenDriver);
    }
    //Locators
    By currentPasswordInputField = By.cssSelector("#inputProfileCurrentPassword");
    By newUserNameInputField = By.cssSelector("#inputProfileName");
    By saveBtn = By.cssSelector(".btn-submit");

    //Helper Methods
    public void providePassword(String password) {
       findElement(currentPasswordInputField).click();
       findElement(currentPasswordInputField).clear();
       findElement(currentPasswordInputField).sendKeys(password);
    }

    public void enterNewUserName(String newUserName) {
        findElement( newUserNameInputField).click();
        findElement( newUserNameInputField).clear();
        findElement( newUserNameInputField).sendKeys(newUserName);
    }
    public void clickOnSave(){
        findElement(saveBtn).click();
    }

}
