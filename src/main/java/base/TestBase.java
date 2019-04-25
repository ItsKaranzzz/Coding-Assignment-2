package base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import utils.CommonFunctions;
import utils.Constants;

import java.io.FileNotFoundException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class TestBase {

    static protected WebDriver driver;
    static protected Properties props;
    String URL;

    protected void initialization() throws FileNotFoundException {
        props = CommonFunctions.readProperties();
        URL = props.getProperty("URL");
        browserSelection(props.getProperty("browser"));
        driver.get(URL);
        browserSettings();


    }

    protected void browserSelection(String browser) {
        if (browser.equalsIgnoreCase("chrome")) {
            WebDriverManager.chromedriver().setup();
            ChromeOptions options = new ChromeOptions();
            options.addArguments("disable-infobars");
            driver = new ChromeDriver(options);
        } else if (browser.equalsIgnoreCase("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        } else if (browser.equalsIgnoreCase("ie")) {
            WebDriverManager.iedriver().setup();
            driver = new InternetExplorerDriver();
        } else {
            driver = null;
        }
    }

    protected void browserSettings() {

        driver.manage().timeouts().pageLoadTimeout(Constants.PAGELOAD_TIMEOUT, TimeUnit.SECONDS);
        driver.manage().window().maximize();


    }

    protected void tearDown() {
        driver.quit();
    }

}
