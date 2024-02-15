import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

import java.time.Duration;

public class BaseTest {
    public static WebDriver driver = null;
    public static WebDriverWait wait;
    public static String url="";

    @BeforeSuite
    static void setupClass() {
        WebDriverManager.chromedriver().setup();
    }

    //DataProvider for IncorrectLogin data
    @DataProvider(name = "IncorrectLoginProviders")
    public static Object[][] getDataFromDataProvider(){
        return new Object[][] {{"NotExisting@emqil.com","NotExistingPassword"},{"Verify12@gamail.com"," "},{" "," "}};
    }

    @DataProvider(name = "CorrectLoginProviders")
    public static Object[][] getValidDataFromDataProvider(){
        return new Object[][] {{"Verify12@gmail.com","te$t$tudent"}};
    }

    @BeforeMethod
    @Parameters({"BaseURL"})
    public void launchBrowser(String BaseURL){
        //      Added ChromeOptions argument below to fix websocket error
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");

        driver = new ChromeDriver(options);
        //driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); //Implicit wait
        wait = new WebDriverWait(driver,Duration.ofSeconds(4));  //Explicit wait
        url=BaseURL;
        driver.get(url);
    }
    @AfterMethod
    public void closeBrowser(){
        //To close Browser
        driver.quit();
    }

    //LoginPage Helper Methods
    protected void login(String email, String password) {
        enterEmail(email);
        enterPassword(password);
        clickSubmit();
    }
    public static void clickSubmit() {
        WebElement loginBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[type='submit']")));
        //WebElement loginBtn = driver.findElement(By.cssSelector("[type='submit']"));
        loginBtn.click();
    }
    public static void enterPassword(String password) {
        WebElement passwordInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[type='password']")));
        passwordInput.click();
        passwordInput.clear();
        passwordInput.sendKeys(password);
    }
    public static void enterEmail(String email) {
        WebElement emailInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[type='email']")));
        emailInput.click();
        emailInput.clear();
        emailInput.sendKeys(email);
    }

    //Profile & Preferences page Helper Methods
    protected void clickOnSave() {
        WebElement saveBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".btn-submit")));
        saveBtn.click();
    }

    protected void enterNewUserName(String newUserName) {
        WebElement newUsernameInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#inputProfileName")));
        newUsernameInput.click();
        newUsernameInput.clear();
        newUsernameInput.sendKeys(newUserName);
    }

    protected void providePassword(String password) {
       WebElement passwordInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#inputProfileCurrentPassword")));
        passwordInput.click();
        passwordInput.clear();
        passwordInput.sendKeys(password);
    }

    protected void clickOnAvatar() {
        WebElement avatarIcon = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".view-profile .avatar")));
        avatarIcon.click();
    }

   // Playlist Helper methods
    protected void addSongIntoPlaylist() {
        WebElement playlistElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#songResultsWrapper .screen-header .existing-playlists >ul >li:nth-child(6)")));
        playlistElement.click();
    }

    protected void clickAddTo() {
        WebElement addToBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".btn-add-to")));
        addToBtn.click();
    }

    public void clickOnDeletePlaylist()  {
        WebElement deletePlaylistBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".del.btn-delete-playlist")));
        try {
               //For Playlist with added songs
               WebElement downloadAllBtn =wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[title='Download all songs in playlist']")));
               deletePlaylistBtn.click();
               WebElement okBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ok")));
               okBtn.click();
           }
        catch (Exception e){
               //For empty playlist
               deletePlaylistBtn.click();
           }
       }

    public void selectPlaylist() {
        WebElement selectPlaylistElement= wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#playlists>ul>li:nth-child(4)")));
        selectPlaylistElement.click();
    }

    protected void selectFirstSong() {
        WebElement selectSongElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#songResultsWrapper .item-container >table >tr >.title")));
        selectSongElement.click();
    }

    protected void clickViewAll() {
        WebElement viewAllBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".songs>h1>button")));
        viewAllBtn.click();
    }

    protected void searchSong(String song) {
        WebElement searchField= wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[type='search']")));
        searchField.click();
        searchField.clear();
        searchField.sendKeys(song);
    }
    protected String getNotificationText()  {
        WebElement notificationMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.success.show")));
        return notificationMessage.getText();

    }


}