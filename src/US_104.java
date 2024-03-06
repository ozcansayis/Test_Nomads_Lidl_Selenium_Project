import Utilities.BaseDriver;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class US_104 extends BaseDriver {
    @Test
    public void TC_104(){
        driver.get("https://www.lidl.com/");
        JavascriptExecutor js = (JavascriptExecutor) driver;

        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//button[text()='accept cookies']")))).click();
        driver.findElement(By.cssSelector("button[data-test='pleaseSignInPopupCloseButton']")).click();
        driver.findElement(By.xpath("//a[text()='sign in']")).click();
        driver.findElement(By.cssSelector("[name='email']")).sendKeys("testnomads01@gmail.com");
        driver.findElement(By.xpath("//*[@name='password']")).sendKeys("Nomad07++"+ Keys.ENTER);
        driver.findElement(By.cssSelector("[data-test='profileDropdown']")).click();
        driver.findElement(By.xpath("//*[@data-test='profile']")).click();
        driver.findElement(By.xpath("//*[text()='preferences']")).click();
        driver.findElement(By.cssSelector("[name='kidfriendly']")).click();
        driver.findElement(By.xpath("//*[@name='kosher']")).click();
        driver.findElement(By.cssSelector("[name='organic']")).click();

        js.executeScript("arguments[0].scrollIntoView(true); arguments[0].click();",driver.findElement(By.xpath("//*[text()='save']")) );
        WebElement condition= driver.findElement(By.xpath("//*[@aria-live='assertive']"));
        wait.until(ExpectedConditions.visibilityOf(condition));
        Assert.assertTrue("Profile in not updated!",condition.isDisplayed());

        waitAndQuit();
    }
}