package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SearchPage extends BasePage{
    public SearchPage(WebDriver givenDriver) {
        super(givenDriver);
    }
    //Locators
    By viewAllBtn = By.cssSelector(".songs>h1>button");
    By firstSong = By.cssSelector("#songResultsWrapper .item-container >table >tr >.title");
    By addToBtn = By.cssSelector(".btn-add-to");
    By playlistElement = By.cssSelector("#songResultsWrapper .screen-header .existing-playlists >ul >li:nth-child(6)");
    By notificationMessage = By.cssSelector("div.success.show");

    //Helper Methods
    public void  clickViewAll(){
        findElement(viewAllBtn).click();
    }
    public void selectFirstSong(){
        findElement(firstSong).click();
    }
    public void clickAddTo(){
        findElement(addToBtn).click();
    }
    public void addSongIntoPlaylist(){
        findElement(playlistElement).click();
    }
    public String getNotificationText(){
        return findElement(notificationMessage).getText();
    }
}
