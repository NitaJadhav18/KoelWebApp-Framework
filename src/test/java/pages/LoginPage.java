package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage extends BasePage{
    //Constructor
    public LoginPage(WebDriver givenDriver) {
        super(givenDriver);
    }
    //Locators
    By emailField = By.cssSelector("[type='email']");
    By passwordField = By.cssSelector("[type='password']");
    By submitBtn = By.cssSelector("[type='submit']");

    //Page Methods
    public void provideEmail (String email){
        findElement(emailField).sendKeys(email);
    }

    public void providePassword(String password){
        findElement(passwordField).sendKeys(password);
    }

    public void clickLogin(){
        findElement(submitBtn).click();
    }

    public void login(){
        provideEmail("Verify12@gmail.com");
        providePassword("te$t$tudent");
        clickLogin();
    }

    //to get Login button
    public WebElement getLoginBtn(){
        return findElement(submitBtn);
    }
}
