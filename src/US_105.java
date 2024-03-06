import Utilities.BaseDriver;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class US_105 extends BaseDriver {
    @Test
    public void TC_105(){
        // Navigate to the Lidl homepage
        driver.get("https://www.lidl.com/");

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

        // Navigate to the 'account management' section
        driver.findElement(By.xpath("//*[text()='account management']")).click();

        // Click the button to initiate account deletion and confirm the account deletion
        driver.findElement(By.xpath("//div[@class='delete-account-button']/button")).click();
        driver.findElement(By.cssSelector("[data-test='confirmation-button']")).click();

        waitAndQuit();
    }
}