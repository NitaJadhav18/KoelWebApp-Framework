import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class PlaylistTests extends BaseTest {
    @Test
    public void addSongToPlaylistTest() throws InterruptedException {
        String notificationText = "Added 1 song into";
        openLoginUrl();
        enterEmail("Verify12@gmail.com");
        enterPassword("te$t$tudent");
        clickSubmit();
        Thread.sleep(2000);
        //search a song
        searchSong("pluto");
        //click on view all
        clickViewAll();
        Thread.sleep(2000);
        //select first song
        selectFirstSong();
        //click addto to add
        clickAddTo();
        Thread.sleep(2000);
        //choose playlist to add
        choosePlaylist();
        Thread.sleep(2000);

        //Assert notification message
        Assert.assertTrue(getNotificationText().contains(notificationText));


    }
}
