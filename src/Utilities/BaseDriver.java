package Utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BaseDriver {
    public static WebDriver driver;
    public static WebDriverWait wait;

    static {
        Logger logger = Logger.getLogger("");
        logger.setLevel(Level.SEVERE);
        driver = new ChromeDriver();
//      driver=new FirefoxDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(15));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        wait = new WebDriverWait(driver,Duration.ofSeconds(15));
    }

    public static void waitAndQuit() {
        MyMethods.myWait(4);
        driver.quit();
    }
}
