package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class HomePage extends BasePage{
    //Constructor
    public HomePage(WebDriver givenDriver) {
        super(givenDriver);
    }
    //Locators
    By userAvatarIcon = By.cssSelector("a>.avatar");
    By searchInputField = By.cssSelector("[type='search']");
    By selectPlaylistElement = By.cssSelector("#playlists>ul>li:nth-child(4)");
    By avatarName = By.cssSelector(".view-profile .name");
    By allSongsElement = By.cssSelector("li a.songs");
  //  By playBtn = By.cssSelector(".play .fa.fa-play");

    //Page Methods
    public WebElement getUserAvatar(){
        return findElement(userAvatarIcon);
    }

    //for song search
    public void searchSong(String song){
        findElement(searchInputField).click();
        findElement(searchInputField).clear();
        findElement(searchInputField).sendKeys(song);
    }
    public void  selectPlaylist(){
        findElement(selectPlaylistElement).click();
    }
    public void choosePlaylistByName(String playlistName) {
        findElement(By.xpath("//section[@id='playlists'] //a[contains(text(),'" + playlistName + "')]")).click();
    }

    public void clickOnAvatar(){
        findElement(userAvatarIcon).click();
    }
    //get avatar Name
    public String getAvatarName(){
        return findElement(avatarName).getText();
    }
    public void chooseAllSongsList(){
        findElement(allSongsElement).click();
    }
    //hover to play back
    public WebElement hoverPlay() {
        WebElement playBtn = driver.findElement(By.cssSelector(".play .fa.fa-play"));
        actions.moveToElement(playBtn).perform();  //For Mouse hover
        return playBtn;
    }
}
