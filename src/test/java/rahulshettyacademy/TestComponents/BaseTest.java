package rahulshettyacademy.TestComponents;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import pageobjects.LandingPage;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

public class BaseTest {


    public WebDriver driver;
    public LandingPage landingPage;
    public WebDriver initializeDriver() throws IOException {


//        Using global properties from file
        Properties properties = new Properties();
        FileInputStream file = new FileInputStream(System.getProperty("user.dir")+"//src//main//java//resources//GlobalData.properties");
        properties.load(file);

        String browser = properties.getProperty("browser");

        if(browser.equalsIgnoreCase("chrome")){
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        } else if (browser.equalsIgnoreCase("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        } else if (browser.equalsIgnoreCase("edge")) {
            WebDriverManager.edgedriver().setup();
            driver = new EdgeDriver();
        }

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        return driver;
    }


    public String getScreenshot(String testCaseName, WebDriver driver) throws IOException {

        TakesScreenshot ts = (TakesScreenshot)driver;
        File source = ts.getScreenshotAs(OutputType.FILE);
        File file = new File(System.getProperty("user.dir")+"//reports//" + testCaseName +".png");
        FileUtils.copyFile(source, file);
        return System.getProperty("user.dir")+"//reports//" + testCaseName +".png";
    }

    @BeforeMethod
    public LandingPage launchApplication() throws IOException {

        WebDriver driver = initializeDriver();
        landingPage = new LandingPage(driver);
        landingPage.goTo();
        return landingPage;
    }

    @AfterMethod
    public void tearDown() {
        driver.close();
    }

}

