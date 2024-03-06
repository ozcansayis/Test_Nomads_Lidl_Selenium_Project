import Utilities.BaseDriver;
import Utilities.MyMethods;
import org.junit.Assert;
import org.junit.Test;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;


import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.Iterator;
import java.util.Set;


public class US_102 extends BaseDriver {
    @Test
    public void TC_102() throws AWTException {
        Robot robot = new Robot();

        driver.get("https://www.lidl.com/");

        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button[aria-label='okay']"))).click();

        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//button[text()='accept cookies']")))).click();


        wait.until(ExpectedConditions.visibilityOfElementLocated((By.cssSelector("[data-test='signIn']")))).click();

        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a[data-test='joinButton']"))).click();

        Assert.assertTrue("hatali site", driver.getCurrentUrl().equals("https://www.lidl.com/register"));


        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("input[id='input2']"))).sendKeys("sam");
        driver.findElement(By.cssSelector("input[id='input3']")).sendKeys("yeks");
        driver.findElement(By.cssSelector("input[id='input4']")).sendKeys("samfromda01@hotmail.com");
        driver.findElement(By.cssSelector("input[id='input5']")).sendKeys("samfromda01@hotmail.com");
        driver.findElement(By.cssSelector("input[id='input6']")).sendKeys("testNomads01.");
        driver.findElement(By.cssSelector("input[id='input7']")).sendKeys("4213213243");
        driver.findElement(By.cssSelector("input[id='input8']")).sendKeys("01113");
        driver.findElement(By.cssSelector("input[id='date-picker-dialog']")).sendKeys("05072001");

        driver.findElement(By.cssSelector("input[id='input9']")).sendKeys("usa cali");
        driver.findElement(By.cssSelector("input[id='input10']")).sendKeys("orange county");
        WebElement element = driver.findElement(By.cssSelector("select[id='select0']"));
        Select select = new Select(element);
        select.selectByVisibleText("California");

        driver.findElement(By.cssSelector("label[class='newsletter labeled-checkbox']")).click();

        driver.findElement(By.cssSelector("div[class='spinnie-container']")).click();

        driver.findElement(By.cssSelector("li[class='sign-in-container']")).click();
       //sign out not to stay logged in for re-registration with the same credentials
        driver.findElement(By.cssSelector("li[tabindex='-1']")).click();

        MyMethods.myWait(2);

        //open a new tab to re-register.
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_T);
        robot.keyRelease(KeyEvent.VK_T);
        robot.keyRelease(KeyEvent.VK_CONTROL);

        MyMethods.myWait(2);


        Set<String> x = driver.getWindowHandles();

        Iterator iterator = x.iterator();
        String ilkSayfa = iterator.next().toString();
        String ikiSayfa = iterator.next().toString();
        MyMethods.myWait(2);
        driver.switchTo().window(ikiSayfa);
        driver.get("https://www.lidl.com/register");

        //CASE 2
        WebElement a = driver.findElement(By.cssSelector("input[id='input2']"));
        MyMethods.myWait(3);
        a.click();
        a.sendKeys("samfromda01@hotmail.com");


        WebElement register2 = driver.findElement(By.cssSelector("[id='input2-helper-text']"));
        Assert.assertTrue("register e-mail accepted twice", register2.getText().contains("This email is already registered. Want to"));

        //CASE 5
        driver.findElement(By.cssSelector("input[id='input5']")).sendKeys("4213213243");
        WebElement number2 = wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("[id='input5-helper-text']"))));
        Assert.assertTrue("duplicated Number", number2.getText().equals("This phone number is already being used, please use another one."));

        //CASE 7
        driver.findElement(By.cssSelector("input[id='input6']")).sendKeys("0111");
        WebElement zipCode = wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("[id='input6-helper-text']"))));
        Assert.assertTrue("duplicated zip number", zipCode.getText().equals("Enter a valid zipcode."));

        //CASE 9
        WebElement birth = driver.findElement(By.cssSelector("input[id='date-picker-dialog']"));
        birth.sendKeys("05011922");
        WebElement birthdate = wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("[id='date-picker-dialog-helper-text']"))));
        Assert.assertTrue("overaged", birthdate.getText().equals("Age must be less than 100."));
        birth.sendKeys(Keys.CONTROL + "a");
        birth.sendKeys(Keys.DELETE);


        //CASE 11
        MyMethods.myWait(2);
        driver.findElement(By.cssSelector("input[id='date-picker-dialog']")).sendKeys("10102022");
        MyMethods.myWait(3);
        WebElement underAge = wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("[id='date-picker-dialog-helper-text']"))));
        Assert.assertTrue("underage registered", underAge.getText().equals("You must be at least 13 years old."));

        waitAndQuit();

    }
}
