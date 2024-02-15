import org.testng.Assert;
import org.testng.annotations.Test;

public class PlaylistTests extends BaseTest {
    //Add song into Playlist
    @Test(enabled = false,dataProvider = "CorrectLoginProviders", dataProviderClass = BaseTest.class)
    public void addSongToPlaylistTest(String email,String password) {
        String notificationText = "Added 1 song into";
        login(email, password);
        //search a song
        searchSong("pluto");
        //click on view all
        clickViewAll();
        //select first song
        selectFirstSong();
        //click addto to add
        clickAddTo();
        //choose playlist to add
        addSongIntoPlaylist();
        //Assert notification message
      Assert.assertTrue(getNotificationText().contains(notificationText));
    }

    //Delete Playlist
    @Test(enabled = false,dataProvider = "CorrectLoginProviders" , dataProviderClass = BaseTest.class)
    public void deletePlaylistTest(String email , String password) {
        String deletePlaylistMsg = "Deleted playlist";
        login(email,password);
        selectPlaylist();
        //click on delete playlist
        clickOnDeletePlaylist();
        Assert.assertTrue(getNotificationText().contains(deletePlaylistMsg)); //to check delete playlist notification message
    }


}
