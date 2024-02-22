package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class PlaylistPage extends BasePage{
    public PlaylistPage(WebDriver givenDriver) {
        super(givenDriver);
    }
    //Locators
    @FindBy(css =".del.btn-delete-playlist")
    WebElement deletePlaylistBtn;
    @FindBy(css ="[title='Download all songs in playlist']")
    WebElement downloadAllBtn;
    @FindBy(css =".ok")
    WebElement okBtn;
    @FindBy(css ="div.success.show")
    WebElement notificationMessage;
    @FindBy(css =".meta.text-secondary .meta")
    WebElement playlistDetails;
    @FindBy(css =".playlist:nth-child(3)")
    WebElement playlistElement;
    @FindBy(css ="[name='name']")
    WebElement playlistInputField;
    @FindBy(css ="#playlistWrapper .song-item .title")
    WebElement songElement;

    //Helper Methods
    public void clickOnDeletePlaylist(){
        try {
            //For Playlist with added songs
           findElement((By) downloadAllBtn);
           deletePlaylistBtn.click();
           okBtn.click();
           }
        catch (Exception e){
            //For empty playlist
            deletePlaylistBtn.click();
        }
    }
    public String getNotificationText(){
        return notificationMessage.getText();
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
        return playlistDetails.getText();
    }
    //Rename Playlist by double click
    public void doubleClickPlaylistToRename(String newPlaylistName)  {
        actions.doubleClick(playlistElement).perform();
        //to clear inputfield select all with ctrl A and then Backspace to clear
        playlistInputField.sendKeys(Keys.chord(Keys.CONTROL,"A",Keys.BACK_SPACE));
        playlistInputField.sendKeys(newPlaylistName);
        playlistInputField.sendKeys(Keys.ENTER);
    }
    //Playlist exist or not
    public boolean doesPlaylistExist(String newPlaylistName) {
        WebElement playlistElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text()='"+ newPlaylistName +"']")));
        return playlistElement.isDisplayed();
    }

    //Verify song exist in playlist
    public boolean  doesSongExists(){
       return songElement.isDisplayed();

    }

}
