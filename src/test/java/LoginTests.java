import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;
import java.util.Set;

public class LoginTests extends BaseTest {
    @Test
    public void LoginWithValidEmailPasswordTest() {
    //      Added ChromeOptions argument below to fix websocket error
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");

        WebDriver driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        //Open the Url
        String url = "https://qa.koel.app/";
        driver.get(url);

        //Enter Email
        WebElement emailInput = driver.findElement(By.cssSelector("[type='email']"));
        emailInput.click();
        emailInput.clear();
        emailInput.sendKeys("Verify12@gmail.com");

        //Enter Password
        WebElement passwordInput = driver.findElement(By.cssSelector("[type='password']"));
        passwordInput.click();
        passwordInput.clear();
        passwordInput.sendKeys("te$t$tudent");

        //Click on Log In
        WebElement loginBtn = driver.findElement(By.cssSelector("[type='submit']"));
        loginBtn.click();

        //check avatar for Homepage
        WebElement avatarField = driver.findElement(By.cssSelector("a>.avatar"));
        Assert.assertTrue(avatarField.isDisplayed());
        //To close Browser
        driver.quit();
    }
         @Test
         public void LoginWithEmptyEmailPassword() {
             ChromeOptions options = new ChromeOptions();
             options.addArguments("--remote-allow-origins=*");
             WebDriver driver = new ChromeDriver(options);
             driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
             //Open the Url
             String url = "https://qa.koel.app/";
             driver.get(url);
             //Click on Log In
             WebElement loginBtn = driver.findElement(By.cssSelector("[type='submit']"));
             loginBtn.click();
             Assert.assertEquals(driver.getCurrentUrl(), url);
             Assert.assertTrue(loginBtn.isDisplayed());
             driver.quit();
         }
}




