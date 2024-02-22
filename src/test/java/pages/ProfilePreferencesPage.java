package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ProfilePreferencesPage extends BasePage{
    public ProfilePreferencesPage(WebDriver givenDriver) {
        super(givenDriver);
    }
    //Locators
    @FindBy(css ="#inputProfileCurrentPassword")
    WebElement currentPasswordInputField;
    @FindBy(css ="#inputProfileName")
    WebElement newUserNameInputField;
    @FindBy(css =".btn-submit")
    WebElement saveBtn;

    //Helper Methods
    public void providePassword(String password) {
       currentPasswordInputField.click();
      currentPasswordInputField.clear();
       currentPasswordInputField.sendKeys(password);
    }

    public void enterNewUserName(String newUserName) {
        newUserNameInputField.click();
         newUserNameInputField.clear();
         newUserNameInputField.sendKeys(newUserName);
    }
    public void clickOnSave(){
        saveBtn.click();
    }

}
