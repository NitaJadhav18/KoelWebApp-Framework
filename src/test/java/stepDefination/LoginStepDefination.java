package stepDefination;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import pages.HomePage;
import pages.LoginPage;
import java.time.Duration;

public class LoginStepDefination {

    @Given("I open Login Page")
    public void iOpenLoginPage(){
        BaseStepDefination.getDriver().get("https://qa.koel.app/");
    }

    @When("I enter email {string}")
    public void iEnterEmail(String email) {
        LoginPage loginPage = new LoginPage(BaseStepDefination.getDriver());
        loginPage.provideEmail(email);
    }

    @And("I enter password {string}")
    public void iEnterPassword(String password) {
        LoginPage loginPage = new LoginPage(BaseStepDefination.getDriver());
        loginPage.providePassword(password);
    }

    @And("I click Login")
    public void iClickLogin() {
        LoginPage loginPage = new LoginPage(BaseStepDefination.getDriver());
        loginPage.clickLogin();
    }

    @Then("I am at HomePage")
    public void iAmAtHomePage() {
        HomePage homePage = new HomePage(BaseStepDefination.getDriver());
        homePage.getUserAvatar();
    }

    @Then("I am at LoginPage")
    public void iAmAtLoginPage() {
        LoginPage loginPage = new LoginPage(BaseStepDefination.getDriver());
        Assert.assertTrue(loginPage.getLoginBtn().isDisplayed());
    }
}
