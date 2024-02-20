import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

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

    //Testcase to assert that a playlist has expected number of songs
    @Test(enabled = false,dataProvider = "CorrectLoginProviders" , dataProviderClass = BaseTest.class)
    public void CountSongsIntoPlaylistTest(String email , String password)  {
        login(email, password);
        choosePlaylistByName("test");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        displayAllSongs();
        //Verifies if the playlist details song count is equal to retrieved number of songs
        Assert.assertTrue(getPlaylistDetails().contains(String.valueOf(songCount())));
    }

    //Testcase to rename playlist by double click
    @Test(dataProvider = "CorrectLoginProviders" , dataProviderClass = BaseTest.class)
    public void renamePlaylistTest(String email, String password) {
        String newPlaylistName = "My Songs";
        login(email, password);
        doubleClickPlaylistToRename("My Songs");

       Assert.assertTrue(doesPlaylistExist(newPlaylistName)); //To Verify NewPlaylist is existed or not
    }
}
