import org.testng.Assert;
import org.testng.annotations.Test;
import pages.AllSongsPage;
import pages.HomePage;
import pages.LoginPage;

public class SongTests extends BaseTest{

    //Playing a song with context click(Right Click)
    @Test(enabled = false)
    public void playSong()  {
        LoginPage loginPage = new LoginPage(driver);
        HomePage homePage = new HomePage(driver);
        AllSongsPage allSongsPage = new AllSongsPage(driver);
        loginPage.login();
        homePage.chooseAllSongsList();   //click on All Songs
        allSongsPage.contextClickFirstSong();  //Right-click on first song
        allSongsPage.choosePlayOption();     //choose play option
        Assert.assertTrue(allSongsPage.isSongPlaying()); //check song is playing or not
    }

    //Testcase for Hover Play button
    @Test(enabled = false)
    public void hoverPlayTest() throws InterruptedException {
        LoginPage loginPage = new LoginPage(driver);
        HomePage homePage = new HomePage(driver);
        loginPage.login();
        homePage.chooseAllSongsList();   //select AllSongs
        Assert.assertTrue(homePage.hoverPlay().isDisplayed());  //Verifies play back displays or not
    }

}
