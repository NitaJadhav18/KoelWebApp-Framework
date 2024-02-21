package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AllSongsPage extends BasePage{
    public AllSongsPage(WebDriver givenDriver) {
        super(givenDriver);
    }

    //Locators
    By firstSongElement = By.cssSelector(".item-container td:nth-child(2)");
    By playElement = By.cssSelector(".playback");
    By visulizerElement = By.cssSelector("[title='Click for a marvelous visualizer!']");
    By selectPlaylistElement = By.cssSelector("#playlists>ul>li:nth-child(4)");



    //Helper Methods
    //contextClick(Right-click) on first song
    public void contextClickFirstSong(){
        actions.contextClick(findElement(firstSongElement)).perform();
    }
    public void choosePlayOption(){
        findElement(playElement).click();
    }
    public boolean isSongPlaying(){
        return findElement(visulizerElement).isDisplayed();
    }
    //add song into playlist with drag and drop
    public void addSongPlaylistDragAndDrop(){
        actions.dragAndDrop(findElement(firstSongElement),findElement(selectPlaylistElement)).build().perform();
    }


}
