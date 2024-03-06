import Utilities.BaseDriver;
import Utilities.MyMethods;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.security.Key;

public class US_103 extends BaseDriver {
    @Test
    public void TC_103() {
        // The home page was accessed successfully.
        driver.get("https://www.lidl.com/");

        // Clickable "accept cookies" or "pop-up" options on the screen to turn it off.
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//button[text()='accept cookies']")))).click();
        driver.findElement(By.cssSelector("button[data-test='pleaseSignInPopupCloseButton']")).click();

        // The "sign in" button on the home page was clicked.
        driver.findElement(By.cssSelector("a[data-test='signIn']")).click();

        // After entering the current e-mail and password information, the "sing in" button was clicked.
        driver.findElement(By.xpath("//input[@name='email']")).sendKeys("testnomads01@gmail.com");
        driver.findElement(By.xpath("//input[@name='password']")).sendKeys("Nomad07++");
        driver.findElement(By.cssSelector("button[data-test='signInButton']")).click();

        // The account page was accessed by clicking on the "My Account" link in the upper right corner of the page.
        driver.findElement(By.xpath("//span[text()='my account']")).click();

        //The "Edit Account" page was accessed.
        driver.findElement(By.cssSelector("div[class='name']")).click();
        MyMethods.myWait(1);

        // Checked that the URL of the account page is "https://www.lidl.com/profile/general".
        String url = driver.getCurrentUrl();
        //
        if (url.equals("https://www.lidl.com/profile/general")) {
            System.out.println("Correct URL, you are on the Profile page.");
        } else {
            System.out.println("You are on the wrong page.");
        }

        // Name and surname information has been updated on the "Edit Account" page.
        WebElement updateName = driver.findElement(By.xpath("//input[@name='firstName']"));
        updateName.sendKeys(Keys.CONTROL + "a" + Keys.DELETE);
        updateName.sendKeys("Determined");

        WebElement updateSurname = driver.findElement(By.cssSelector("input[name='lastName']"));
        updateSurname.sendKeys(Keys.CONTROL + "a" + Keys.DELETE);
        updateSurname.sendKeys("Team");

        // Updates were saved by clicking on the "Save" button.
        driver.findElement(By.xpath("//button[text()='save']")).click();
        MyMethods.myWait(2);

        // It was checked that the message "profile updated successfully." was displayed.
        WebElement message = driver.findElement(By.cssSelector("p[aria-live='assertive']"));
        Assert.assertEquals("Message not found.", "profile updated successfully", message.getText());


        // Checked that the updated username is displayed in the top right corner of the page.
        String update = driver.findElement(By.cssSelector("h3[class='title-3']")).getText();
        if (update.contains(updateName.getText()) && update.contains(updateSurname.getText())) {
            System.out.println("Update successful.");
        } else {
            System.out.println("Update failed!");
        }
        waitAndQuit();
    }
}
