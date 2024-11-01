package TestComponents;

import PageObjects.LoginPage;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

public class BaseTest{
    public WebDriver driver;
    public LoginPage lp;
    public WebDriver InitializeBrowser() throws IOException {

        Properties prop = new Properties();
        FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "/src/main/java/Resourses/Globledata.properties");
        prop.load(fis);
        //String browserName = prop.getProperty("browser");
        String browserName = System.getProperty("browser")!=null?System.getProperty("browser"):prop.getProperty("browser");
        if(browserName.contains("chrome"))
        {
            ChromeOptions options = new ChromeOptions();
            if(browserName.contains("headless"))
            {
                options.addArguments("headless");

            }
            driver = new ChromeDriver(options);
        } else if (browserName.contains("edge")) {
            driver = new EdgeDriver();
        } else if (browserName.contains("firefox")) {
            driver = new FirefoxDriver();
        }
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        return driver;
    }
    public String getScreenShort(String TestcaseName, WebDriver driver) throws IOException {
        TakesScreenshot sc = (TakesScreenshot) driver;
        File Source = sc.getScreenshotAs(OutputType.FILE);
        File destination = new File(System.getProperty("user.dir")+"//reportes//"+TestcaseName+".png");
        FileUtils.copyFile(Source,destination);
        return System.getProperty("user.dir")+"//reportes//"+TestcaseName+".png";

    }

    @BeforeMethod(alwaysRun = true)
    public void launchApp() throws IOException {
        InitializeBrowser();
        lp = new LoginPage(driver);
        lp.Url("https://rahulshettyacademy.com/client");
    }





}
