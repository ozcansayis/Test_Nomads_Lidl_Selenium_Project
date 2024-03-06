import Utilities.BaseDriver;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class US_105 extends BaseDriver {
    @Test
    public void TC_105(){
        driver.get("https://www.lidl.com/");

        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//button[text()='accept cookies']")))).click();
        driver.findElement(By.cssSelector("button[data-test='pleaseSignInPopupCloseButton']")).click();
        driver.findElement(By.xpath("//a[text()='sign in']")).click();
        driver.findElement(By.cssSelector("[name='email']")).sendKeys("testnomads01@gmail.com");
        driver.findElement(By.xpath("//*[@name='password']")).sendKeys("Nomad07++"+ Keys.ENTER);
        driver.findElement(By.cssSelector("[data-test='profileDropdown']")).click();
        driver.findElement(By.xpath("//*[@data-test='profile']")).click();
        driver.findElement(By.xpath("//*[text()='account management']")).click();
        driver.findElement(By.xpath("//div[@class='delete-account-button']/button")).click();
        driver.findElement(By.cssSelector("[data-test='confirmation-button']")).click();

        waitAndQuit();
    }
}