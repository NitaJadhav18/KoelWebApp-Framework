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
import org.openqa.selenium.firefox.FirefoxOptions;
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
import java.net.URL;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class BaseTest {
    public static WebDriver driver = null;
    public static WebDriverWait wait = null;
    public static Actions actions = null;
    public static String url= null;
    public static final ThreadLocal<WebDriver> threadDriver = new ThreadLocal<>();

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
    public void setUpBrowser(String BaseURL) throws MalformedURLException {
        threadDriver.set(pickBrowser(System.getProperty("browser")));
       // driver = pickBrowser(System.getProperty("browser"));
        getDriver().manage().window().maximize();
        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); //Implicit wait
        wait = new WebDriverWait(getDriver(),Duration.ofSeconds(5));  //Explicit wait
        actions = new Actions(getDriver());   //Action class
        url=BaseURL;
        getDriver().get(url);
    }
    //This getDriver method returns the current instance of WebDriver associated with current thread
    public static WebDriver getDriver(){
        return threadDriver.get();
    }
    public static WebDriver pickBrowser(String browser) throws MalformedURLException {
        DesiredCapabilities caps = new DesiredCapabilities();
        String gridURL = "http://192.168.1.101:4444";
        ChromeOptions options =new ChromeOptions();
        //      Added ChromeOptions argument below to fix websocket error
        options.addArguments("--remote-allow-origins=*");
        switch(browser){
            case "chrome":
                WebDriverManager.chromedriver().setup();
                return driver = new ChromeDriver(options);
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
            case "cloud":     //gradle clean test -Dbrowser=cloud
                return lambdaTest();
            default:
                WebDriverManager.chromedriver().setup();
                return driver = new ChromeDriver(options);
        }
    }
    //method for cross browser testing using Lambda test
    public static WebDriver lambdaTest() throws MalformedURLException {
       String hubURL = "https://hub.lambdatest.com/wd/hub";

        FirefoxOptions browserOptions = new FirefoxOptions();
        browserOptions.setPlatformName("Windows 10");
        browserOptions.setBrowserVersion("123.0");
        HashMap<String, Object> ltOptions = new HashMap<String, Object>();
        ltOptions.put("username", "nita.jadhav");
        ltOptions.put("accessKey", "RvyOZvpNoM4MmuOFq6fiU7BhuYmEQn4vdlKHQIW4O22B8j1YEt");
        ltOptions.put("project", "Untitled");
        ltOptions.put("selenium_version", "4.0.0");
        ltOptions.put("w3c", true);
        browserOptions.setCapability("LT:Options", ltOptions);
        return new RemoteWebDriver(new URL(hubURL),browserOptions);
    }

    //tearDown method is executed after each test method
    //and its purpose is to close the WebDriver and remove its instance from threadlocal
    @AfterMethod
    public void tearDown(){
        //To close Browser
        getDriver().quit();
        threadDriver.remove();
    }
   }