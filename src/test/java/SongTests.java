import org.testng.Assert;
import org.testng.annotations.Test;

public class SongTests extends BaseTest{

    //Playing a song with context click(Right Click)
    @Test(enabled = false,dataProvider = "CorrectLoginProviders" , dataProviderClass = BaseTest.class)
    public void playSong(String email,String password)  {
        login(email, password);
        chooseAllSongsList();
        contextClickFirstSong();
        choosePlayOption();
        Assert.assertTrue(isSongPlaying()); //check song is playing or not
    }

    //Testcase for Hover Play button
    @Test(enabled = true,dataProvider = "CorrectLoginProviders" , dataProviderClass = BaseTest.class)
    public void hoverPlayTest(String email, String password) {
        login(email, password);
        chooseAllSongsList();
        hoverPlay();
        Assert.assertTrue(hoverPlay().isDisplayed());
    }

}
