package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage {
    WebDriver driver;
    WebDriverWait wait;
    Actions actions;

    //WebElements
    @FindBy(css ="li a.songs")
    WebElement allSongsElement;
    @FindBy(css ="[title='Click for a marvelous visualizer!']")
    WebElement visulizerElement;
    @FindBy(css =".overlay .loading")
    WebElement overlayLocator;


    public BasePage(WebDriver givenDriver) {
        driver = givenDriver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        actions = new Actions(driver);
        PageFactory.initElements(driver,this);
    }

    public WebElement findElement(By locator){
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    //Context click(Right-click)
    public void contextClick(WebElement locator){
        //WebElement contexClickElement = wait.until(ExpectedConditions.visibilityOfElementLocated((By) locator));
        actions.contextClick(locator).perform();
    }
    //Hover  mouse to playback
    public void hoverAction(WebElement locator){
       // WebElement playElement = driver.findElement((By) locator);
        actions.moveToElement(locator).build().perform();
    }
    //For click
    public void click(WebElement locator){
        locator.click();
    }
    //For double-clicked
    public void doubleClick(WebElement locator){
        actions.doubleClick(locator).perform();
    }
    public void waitForOverlayToGoAway(){
        wait.until(ExpectedConditions.invisibilityOf(overlayLocator));
    }
    public void chooseAllSongsList(){
       allSongsElement.click();
    }
    //Verifies song is playing
    public boolean isSongPlaying(){
        return visulizerElement.isDisplayed();
    }
}
