import Utilities.BaseDriver;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class US_104 extends BaseDriver {
    @Test
    public void TC_104(){
        // Navigate to website
        driver.get("https://www.lidl.com/");
        JavascriptExecutor js = (JavascriptExecutor) driver;

        // Wait for the 'accept cookies' button to be clickable and then click it
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//button[text()='accept cookies']")))).click();
        driver.findElement(By.cssSelector("button[data-test='pleaseSignInPopupCloseButton']")).click();

        // Navigate to the sign-in page
        driver.findElement(By.xpath("//a[text()='sign in']")).click();

        // Enter email and password then press enter key.
        driver.findElement(By.cssSelector("[name='email']")).sendKeys("testnomads01@gmail.com");
        driver.findElement(By.xpath("//*[@name='password']")).sendKeys("Nomad07++"+ Keys.ENTER);

        // Open the profile dropdown menu
        driver.findElement(By.cssSelector("[data-test='profileDropdown']")).click();

        // Navigate to the profile
        driver.findElement(By.xpath("//*[@data-test='profile']")).click();

        // Go to preferences section
        driver.findElement(By.xpath("//*[text()='preferences']")).click();

        // Toggle 'kid friendly','kosher' and 'organic' option
        driver.findElement(By.cssSelector("[name='kidfriendly']")).click();
        driver.findElement(By.xpath("//*[@name='kosher']")).click();
        driver.findElement(By.cssSelector("[name='organic']")).click();

        // Use JavaScript to scroll to the 'save' button and click it.
        js.executeScript("arguments[0].scrollIntoView(true); arguments[0].click();",driver.findElement(By.xpath("//*[text()='save']")) );
        WebElement condition= driver.findElement(By.xpath("//*[@aria-live='assertive']"));
        wait.until(ExpectedConditions.visibilityOf(condition));

        // Assert that the profile update confirmation message is displayed
        Assert.assertTrue("Profile in not updated!",condition.isDisplayed());

        waitAndQuit();
    }
}