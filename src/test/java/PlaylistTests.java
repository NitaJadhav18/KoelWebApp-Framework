import org.testng.Assert;
import org.testng.annotations.Test;
import pages.*;

import java.time.Duration;

public class PlaylistTests extends BaseTest {

    //Add song into Playlist
    @Test(enabled = true)
    public void addSongToPlaylistTest()  {
        LoginPage loginPage = new LoginPage(getDriver());
        HomePage homePage = new HomePage(getDriver());
        SearchPage searchPage = new SearchPage(getDriver());
        String notificationText = "Added 1 song into";
         loginPage.login();
         homePage.searchSong("pluto");  //search a song
        searchPage.clickViewAll();    //click on view all
         searchPage.selectFirstSong();  //select first song
         searchPage.clickAddTo();        //click addto to add
         searchPage.addSongIntoPlaylist();      //choose playlist to add
        //Assert notification message
      Assert.assertTrue(searchPage.getNotificationText().contains(notificationText));
    }


    //Testcase to assert that a playlist has expected number of songs
    @Test(enabled = true)
    public void CountSongsIntoPlaylistTest() {
        LoginPage loginPage = new LoginPage(getDriver());
        HomePage homePage = new HomePage(getDriver());
        PlaylistPage playlistPage = new PlaylistPage(getDriver());
        loginPage.login();
        homePage.choosePlaylistByName("test");
        //driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        playlistPage.displayAllSongs();
        //Verifies if the playlist details song count is equal to retrieved number of songs
        Assert.assertTrue(playlistPage.getPlaylistDetails().contains(String.valueOf(playlistPage.songCount())));
    }


    //Add songs from All songs into playlist with drag and drop
    @Test(enabled = true)
    public void addSongsIntoPlaylist()  {
        LoginPage loginPage = new LoginPage(getDriver());
        HomePage homePage = new HomePage(getDriver());
        AllSongsPage allSongsPage = new AllSongsPage(getDriver());
        PlaylistPage playlistPage = new PlaylistPage(getDriver());
        loginPage.login();
        homePage.chooseAllSongsList();   //select All Songs
        allSongsPage.addSongPlaylistDragAndDrop();  //drag and drop song
        homePage.selectPlaylist();   //select playlist
        Assert.assertTrue(playlistPage.doesSongExists());   //Verifies song exist or not
    }
    //Testcase to rename playlist by double click
    @Test(enabled = true )
    public void renamePlaylistTest()  {
        String newPlaylistName = "My Songs";
        LoginPage loginPage = new LoginPage(getDriver());
        PlaylistPage playlistPage = new PlaylistPage(getDriver());
        loginPage.login();
        playlistPage.doubleClickPlaylistToRename(newPlaylistName);

        Assert.assertTrue(playlistPage.doesPlaylistExist(newPlaylistName)); //To Verify NewPlaylist is existed or not
    }
    //Delete Playlist
    @Test(enabled = true)
    public void deletePlaylistTest() {
        LoginPage loginPage = new LoginPage(getDriver());
        HomePage homePage = new HomePage(getDriver());
        PlaylistPage playlistPage = new PlaylistPage(getDriver());
        String deletePlaylistMsg = "Deleted playlist";
        loginPage.login();
        homePage.selectPlaylist();   //select playlist to delete
        playlistPage.clickOnDeletePlaylist();  //click on delete playlist
        Assert.assertTrue(playlistPage.getNotificationText().contains(deletePlaylistMsg)); //to check delete playlist notification message
    }

}
