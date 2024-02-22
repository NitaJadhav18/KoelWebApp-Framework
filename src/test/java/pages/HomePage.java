package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class HomePage extends BasePage {
    //Constructor
    public HomePage(WebDriver givenDriver) {
        super(givenDriver);
    }

    //Locators
    @FindBy(css ="a>.avatar")
    WebElement userAvatarIcon;
    @FindBy(css ="[type='search']")
    WebElement searchInputField;
    @FindBy(css ="#playlists>ul>li:nth-child(4)")
    WebElement selectPlaylistElement;
    @FindBy(css =".view-profile .name")
    WebElement avatarName;
    @FindBy(css =".play .fa.fa-play")
    WebElement playBtn;

    //Page Methods
    public WebElement getUserAvatar() {
        return userAvatarIcon;
    }

    //for song search
    public void searchSong(String song) {
       searchInputField.click();
       searchInputField.clear();
       searchInputField.sendKeys(song);
    }

    public void selectPlaylist() {
        selectPlaylistElement.click();
    }

    public void choosePlaylistByName(String playlistName) {
        findElement(By.xpath("//section[@id='playlists'] //a[contains(text(),'" + playlistName + "')]")).click();
    }

    public void clickOnAvatar() {
        userAvatarIcon.click();
    }

    //get avatar Name
    public String getAvatarName() {
        return avatarName.getText();
    }

    //hover to play back
    public WebElement hoverPlay() {
        hoverAction(playBtn);
        return playBtn;
    }

}