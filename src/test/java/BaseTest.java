import io.github.bonigarcia.wdm.WebDriverManager;
import org.bouncycastle.oer.Switch;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;
import pages.HomePage;
import pages.LoginPage;

import java.net.MalformedURLException;
import java.net.URI;
import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class BaseTest {
    public static WebDriver driver = null;
    public static WebDriverWait wait = null;
    public static Actions actions = null;
    public static String url= null;

    @BeforeSuite
    static void setupClass() {
        WebDriverManager.chromedriver().setup();
    }

    //DataProvider for IncorrectLogin data
    @DataProvider(name = "IncorrectLoginProviders")
    public static Object[][] getDataFromDataProvider(){
        return new Object[][] {{"NotExisting@emqil.com","NotExistingPassword"},{"Verify12@gamail.com"," "},{" "," "}};
    }

    @DataProvider(name = "CorrectLoginProviders")
    public static Object[][] getValidDataFromDataProvider(){
        return new Object[][] {{"Verify12@gmail.com","te$t$tudent"}};
    }

    @BeforeMethod
    @Parameters({"BaseURL"})
    public void launchBrowser(String BaseURL) throws MalformedURLException {
        driver = pickBrowser(System.getProperty("browser"));
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); //Implicit wait
        wait = new WebDriverWait(driver,Duration.ofSeconds(5));  //Explicit wait
        actions = new Actions(driver);   //Action class
        url=BaseURL;
        driver.get(url);
    }
    public static WebDriver pickBrowser(String browser) throws MalformedURLException {
        DesiredCapabilities caps = new DesiredCapabilities();
        String gridURL = "http://192.168.1.101:4444";
        switch(browser){
            case "firefox":  //gradle clean test -Dbrowser=firefox
                WebDriverManager.firefoxdriver().setup();
                return driver = new FirefoxDriver();
            case "MicrosoftEdge":    //gradle clean test -Dbrowser=MicrosoftEdge
                WebDriverManager.edgedriver().setup();
                EdgeOptions edgeoptions =new EdgeOptions();
                edgeoptions.addArguments("--remote-allow-origins=*");
                return driver = new EdgeDriver(edgeoptions);
            case "grid-edge":       //gradle clean test -Dbrowser=grid-edge
                caps.setCapability("browserName","MicrosoftEdge");
                return driver = new RemoteWebDriver(URI.create(gridURL).toURL(),caps);
            case "grid-firefox":     //gradle clean test -Dbrowser=grid-firefox
                caps.setCapability("browserName","firefox");
                return driver = new RemoteWebDriver(URI.create(gridURL).toURL(),caps);
            case "grid-chrome":     //gradle clean test -Dbrowser=grid-chrome
                caps.setCapability("browserName","chrome");
                return driver = new RemoteWebDriver(URI.create(gridURL).toURL(),caps);
            default:
                WebDriverManager.chromedriver().setup();
                //      Added ChromeOptions argument below to fix websocket error
                ChromeOptions options =new ChromeOptions();
                options.addArguments("--remote-allow-origins=*");
                return driver = new ChromeDriver(options);
        }
    }
    @AfterMethod
    public void closeBrowser(){
        //To close Browser
        driver.quit();
    }

   }