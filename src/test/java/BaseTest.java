import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

import java.time.Duration;
import java.util.List;

public class BaseTest {
    public static WebDriver driver = null;
    public static WebDriverWait wait;
    public static Actions actions;
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
       // WebDriverManager.chromedriver().clearDriverCache().setup();
        driver = new ChromeDriver(options);
        //driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); //Implicit wait
        wait = new WebDriverWait(driver,Duration.ofSeconds(4));  //Explicit wait
        actions = new Actions(driver);   //Action class
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

//Song play Helper methods
    public boolean isSongPlaying() {
        return driver.findElement(By.cssSelector("[title='Click for a marvelous visualizer!']")).isDisplayed();
    }

    public void choosePlayOption() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".playback"))).click();
    }
    //Right click on first song of All Songs
    public void contextClickFirstSong()  {
        WebElement firstSongElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".item-container td:nth-child(2)")));
        actions.contextClick(firstSongElement).perform(); //For Right click
    }

    public void chooseAllSongsList() {
      // wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".overlay.loading")));
       wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("li a.songs"))).click();
           }
    //To hover mover to Play button
    protected WebElement hoverPlay() {
        WebElement playBtn = driver.findElement(By.cssSelector(".play .fa.fa-play"));
        actions.moveToElement(playBtn).perform();  //For Mouse hover
        return playBtn;
    }

    //get playlistDetails
    public String getPlaylistDetails() {
        //return song total of playlist
        return driver.findElement(By.cssSelector(".meta.text-secondary .meta")).getText();
    }

    //Count songs in playlist
    public int songCount() {
       return driver.findElements(By.cssSelector("#playlistWrapper .song-item .title")).size();
    }

    //Display all Song in playlist
    public void displayAllSongs() {
        List<WebElement> songList = driver.findElements(By.cssSelector("#playlistWrapper .song-item .title"));
        System.out.println("Total song count = "+ songCount());
        for (WebElement e :songList){
            System.out.println(e.getText());
        }
    }
    //Choose playlist By name
    public void choosePlaylistByName(String playlistName) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//section[@id='playlists'] //a[contains(text(),'" + playlistName + "')]"))).click();
    }

    public boolean doesPlaylistExist(String newPlaylistName) {
        WebElement playlistElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text()='"+ newPlaylistName +"']")));
        return playlistElement.isDisplayed();
    }

    public void doubleClickPlaylistToRename(String newPlaylistName)  {
        WebElement playlistElement= wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".playlist:nth-child(3)")));
        actions.doubleClick(playlistElement).perform();
        WebElement playlistInputField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[name='name']")));
        //to clear inputfield select all with ctrl A and then Backspace to clear
        playlistInputField.sendKeys(Keys.chord(Keys.CONTROL,"A",Keys.BACK_SPACE));
        playlistInputField.sendKeys(newPlaylistName);
        playlistInputField.sendKeys(Keys.ENTER);
    }
}