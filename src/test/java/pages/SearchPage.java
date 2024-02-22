package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SearchPage extends BasePage{
    public SearchPage(WebDriver givenDriver) {
        super(givenDriver);
    }
    //Locators
    @FindBy(css =".songs>h1>button")
    WebElement viewAllBtn;
    @FindBy(css ="#songResultsWrapper .item-container >table >tr >.title")
    WebElement firstSong;
    @FindBy(css =".btn-add-to")
    WebElement addToBtn;
    @FindBy(css ="#songResultsWrapper .screen-header .existing-playlists >ul >li:nth-child(6)")
    WebElement playlistElement;
    @FindBy(css ="div.success.show")
    WebElement notificationMessage;

    //Helper Methods
    public void  clickViewAll(){
        viewAllBtn.click();
    }
    public void selectFirstSong(){
        firstSong.click();
    }
    public void clickAddTo(){
        addToBtn.click();
    }
    public void addSongIntoPlaylist(){
        playlistElement.click();
    }
    public String getNotificationText(){
        return notificationMessage.getText();
    }
}
