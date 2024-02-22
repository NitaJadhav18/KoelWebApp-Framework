package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage{
    //Constructor
    public LoginPage(WebDriver givenDriver) {
        super(givenDriver);
    }
    //Locators
    @FindBy(css ="[type='email']")
    WebElement emailField;
    @FindBy(css ="[type='password']")
    WebElement passwordField;
    @FindBy(css ="[type='submit']")
    WebElement submitBtn;

    //Page Methods
    public void provideEmail (String email){      emailField.sendKeys(email);    }

    public void providePassword(String password){
        passwordField.sendKeys(password);
    }

    public void clickLogin(){
        submitBtn.click();
    }

    public void login(){
        provideEmail("Verify12@gmail.com");
        providePassword("te$t$tudent");
        clickLogin();
    }

    //to get Login button
    public WebElement getLoginBtn(){
        return submitBtn;
    }
}
