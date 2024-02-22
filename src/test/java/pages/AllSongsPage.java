package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AllSongsPage extends BasePage{
    public AllSongsPage(WebDriver givenDriver) {
        super(givenDriver);
    }

    //Locators
    @FindBy(css =".item-container td:nth-child(2)")
    WebElement firstSongElement;
    @FindBy(css =".playback")
    WebElement playElement;
    @FindBy(css ="#playlists>ul>li:nth-child(4)")
    WebElement selectPlaylistElement;

    //Helper Methods
    //contextClick(Right-click) on first song
    public void contextClickFirstSong(){
        contextClick(firstSongElement);
    }
    public void choosePlayOption(){
        playElement.click();
    }

    //add song into playlist with drag and drop
    public void addSongPlaylistDragAndDrop(){
        actions.dragAndDrop(firstSongElement,selectPlaylistElement).build().perform();
    }


}
