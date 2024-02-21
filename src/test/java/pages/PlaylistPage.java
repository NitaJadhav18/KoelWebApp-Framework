package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class PlaylistPage extends BasePage{
    public PlaylistPage(WebDriver givenDriver) {
        super(givenDriver);
    }
    //Locators
    By deletePlaylistBtn = By.cssSelector(".del.btn-delete-playlist");
    By downloadAllBtn = By.cssSelector("[title='Download all songs in playlist']");
    By okBtn = By.cssSelector(".ok");
    By notificationMessage = By.cssSelector("div.success.show");
    By playlistDetails = By.cssSelector(".meta.text-secondary .meta");
    By playlistElement = By.cssSelector(".playlist:nth-child(3)");
    By playlistInputField = By.cssSelector("[name='name']");

    By songElement = By.cssSelector("#playlistWrapper .song-item .title");

    //Helper Methods
    public void clickOnDeletePlaylist(){
        try {
            //For Playlist with added songs
            findElement(downloadAllBtn);
            findElement(deletePlaylistBtn).click();
            findElement(okBtn).click();
           }
        catch (Exception e){
            //For empty playlist
            findElement(deletePlaylistBtn).click();
        }
    }
    public String getNotificationText(){
        return findElement(notificationMessage).getText();
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
    //get playlistDetails
    public String getPlaylistDetails() {
        //return song total of playlist
        return findElement(playlistDetails).getText();
    }
    //Rename Playlist by double click
    public void doubleClickPlaylistToRename(String newPlaylistName)  {
        actions.doubleClick(findElement(playlistElement)).perform();
        //to clear inputfield select all with ctrl A and then Backspace to clear
        findElement(playlistInputField).sendKeys(Keys.chord(Keys.CONTROL,"A",Keys.BACK_SPACE));
        findElement(playlistInputField).sendKeys(newPlaylistName);
        findElement(playlistInputField).sendKeys(Keys.ENTER);
    }
    //Playlist exist or not
    public boolean doesPlaylistExist(String newPlaylistName) {
        WebElement playlistElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text()='"+ newPlaylistName +"']")));
        return playlistElement.isDisplayed();
    }

    //Verify song exist in playlist
    public boolean  doesSongExists(){
       return findElement(songElement).isDisplayed();

    }

}
