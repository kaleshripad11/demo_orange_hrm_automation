package testbase;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import pages.Page_Dashboard;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.ResourceBundle;

public class Test_Base {
    /**
     * This class will hold all the re-usable methods
     */

    public String screenshotPath;
    public WebDriver driver;
    public ResourceBundle rb;
    public Logger log;
    public WebDriverWait wait;

    @BeforeClass
    @Parameters("browser")
    public void setup(String browser) {
        log = LogManager.getLogger(this);
        rb = ResourceBundle.getBundle("config");
        if (browser.equals("chrome")) {
            log.info("***** Launching chrome browser *****");
            driver = new ChromeDriver();
            ChromeOptions options = new ChromeOptions();
            options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        }
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.get(rb.getString("baseurl"));
    }

    public String captureScreenshots(String testName) throws IOException {
        // Generate time stamp
        String timeStamp = new SimpleDateFormat("yyyy.MM.dd-HH.mm.ss").format(new Date());

        // Type casting webdriver variable
        TakesScreenshot takess = (TakesScreenshot)driver;

        // Generate output file
        File source = takess.getScreenshotAs(OutputType.FILE);

        // Only for debugging
        // String filepath = System.getProperty("user.dir")+"/screenshots/";
        String filepath = "/Users/shripadkale/Documents/Selenium_Java/Demo_OrangeHRM_Automation/screenshots/";
        //log.info("Screenshot file path : "+filepath);

        // Set file path
        File destination = new File( filepath + testName + "-"+ timeStamp + ".png");
        // System.getProperty("user.dir")+"/screenshots/" + testName+ "-"+ timeStamp + ".png";

        try {
            FileUtils.copyFile(source,destination);
        }
        catch(Exception ex) {
            ex.getMessage();
        }
        return destination.toString();
    }


    @AfterSuite
    public void tearDown(){
        driver.quit();
    }
}
