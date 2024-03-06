import Utilities.BaseDriver;
import Utilities.MyMethods;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class US_103 extends BaseDriver {
    @Test
    public void TC_103() {
        driver.get("https://www.lidl.com/");

        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//button[text()='accept cookies']")))).click();
        driver.findElement(By.cssSelector("button[data-test='pleaseSignInPopupCloseButton']")).click();
        driver.findElement(By.cssSelector("a[data-test='signIn']")).click();
        driver.findElement(By.xpath("//input[@name='email']")).sendKeys("testnomads01@gmail.com");
        driver.findElement(By.xpath("//input[@name='password']")).sendKeys("Nomad07++");
        driver.findElement(By.cssSelector("button[data-test='signInButton']")).click();
        driver.findElement(By.xpath("//span[text()='my account']")).click();
        driver.findElement(By.cssSelector("div[class='name']")).click();
        MyMethods.myWait(1);
        String url = driver.getCurrentUrl();
        if (url.equals("https://www.lidl.com/profile/general")) {
            System.out.println("Correct URL, you are on the Profile page.");
        } else {
            System.out.println("You are on the wrong page.");
        }
        WebElement updateName = driver.findElement(By.xpath("//input[@name='firstName']"));
        updateName.sendKeys(Keys.CONTROL + "a" + Keys.DELETE);
        updateName.sendKeys("Determined");
        WebElement updateSurname = driver.findElement(By.cssSelector("input[name='lastName']"));
        updateSurname.sendKeys(Keys.CONTROL + "a" + Keys.DELETE);
        updateSurname.sendKeys("Team");
        driver.findElement(By.xpath("//button[text()='save']")).click();
        MyMethods.myWait(2);

        WebElement message = driver.findElement(By.cssSelector("p[aria-live='assertive']"));
        Assert.assertEquals("Message not found.", "profile updated successfully", message.getText());

        String update = driver.findElement(By.cssSelector("h3[class='title-3']")).getText();
        if (update.contains(updateName.getText()) && update.contains(updateSurname.getText())) {
            System.out.println("Update successful.");
        } else {
            System.out.println("Update failed!");
        }
        waitAndQuit();
    }
}