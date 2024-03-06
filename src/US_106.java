import Utilities.BaseDriver;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class US_106 extends BaseDriver {
    @Test
    public void TC_10(){
        driver.get("https://www.lidl.com/");

        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//button[text()='accept cookies']")))).click();
        driver.findElement(By.cssSelector("button[data-test='pleaseSignInPopupCloseButton']")).click();
        Assert.assertEquals("The URL address is incorrect.","https://www.lidl.com/",driver.getCurrentUrl());
        driver.findElement(By.xpath("//a[text()='sign in']")).click();
        driver.findElement(By.cssSelector("input[id='input0']")).sendKeys("testnomads01@gmail.com");
        driver.findElement(By.cssSelector("input[id='input1']")).sendKeys("Nomad07++");
        driver.findElement(By.cssSelector("button[data-test='signInButton']")).click();

        WebElement myAccount =driver.findElement(By.xpath("//span[text()='my account']"));
        Assert.assertEquals("The login process failed.","my account",myAccount.getText());

        waitAndQuit();
    }
}