import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Practice {
    public static void main(String[] args){
        WebDriverManager.chromedriver().setup();
        WebDriver driver = null;
        driver = new ChromeDriver();
        driver.get("http://www.google.com");
        driver.quit();

    }
}
