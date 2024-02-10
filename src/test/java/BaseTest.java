import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import java.time.Duration;

public class BaseTest {
    public static WebDriver driver = null;
    public static String url="https://qa.koel.app/";

    @BeforeSuite
    static void setupClass() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeMethod
    public void launchBrowser(){
        //      Added ChromeOptions argument below to fix websocket error
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");

        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }
    @AfterMethod
    public void closeBrowser(){
        //To close Browser
        driver.quit();
    }

    //LoginPage Helper Methods
    public static void clickSubmit() {
        WebElement loginBtn = driver.findElement(By.cssSelector("[type='submit']"));
        loginBtn.click();
    }
    public static void enterPassword(String password) {
        WebElement passwordInput = driver.findElement(By.cssSelector("[type='password']"));
        passwordInput.click();
        passwordInput.clear();
        passwordInput.sendKeys(password);
    }
    public static void enterEmail(String email) {
        WebElement emailInput = driver.findElement(By.cssSelector("[type='email']"));
        emailInput.click();
        emailInput.clear();
        emailInput.sendKeys(email);
    }
    public static void openLoginUrl() {
        driver.get(url);
    }

    //Profile & Preferences page Helper Methods
    protected void clickOnSave() {
        WebElement saveBtn= driver.findElement(By.cssSelector(".btn-submit"));
        saveBtn.click();
    }

    protected void enterNewUserName(String newUserName) {
        WebElement newUsernameInput= driver.findElement(By.cssSelector("#inputProfileName"));
        newUsernameInput.click();
        newUsernameInput.clear();
        newUsernameInput.sendKeys(newUserName);
    }

    protected void providePassword() {
        WebElement passwordInput=driver.findElement(By.cssSelector("#inputProfileCurrentPassword"));
        passwordInput.click();
        passwordInput.clear();
        passwordInput.sendKeys("te$t$tudent");
    }

    protected void clickOnAvatar() {
        WebElement avatar= driver.findElement(By.cssSelector(".view-profile .avatar"));
        avatar.click();
    }

   // Playlist Helper methods
    protected void choosePlaylist() {
        WebElement playlistElement = driver.findElement(By.xpath("//section[@id='songResultsWrapper']//li[contains(text( ),'My Songs')]"));
        playlistElement.click();
    }

    protected void clickAddTo() {
        WebElement addToBtn = driver.findElement(By.cssSelector(".btn-add-to"));
        addToBtn.click();
    }

    protected void selectFirstSong() {
        WebElement selectSongElement = driver.findElement(By.cssSelector("#songResultsWrapper .item-container >table >tr >.title"));
        selectSongElement.click();
    }

    protected void clickViewAll() {
        WebElement viewAllBtn = driver.findElement(By.cssSelector(".songs>h1>button"));
        viewAllBtn.click();
    }

    protected void searchSong(String song) {
        WebElement searchField= driver.findElement(By.cssSelector("[type='search']"));
        searchField.click();
        searchField.clear();
        searchField.sendKeys(song);
    }
    protected String getNotificationText() throws InterruptedException {
        Thread.sleep(2000);
        WebElement notificationMessage = driver.findElement(By.cssSelector("div.success.show"));
        return notificationMessage.getText();

    }
}