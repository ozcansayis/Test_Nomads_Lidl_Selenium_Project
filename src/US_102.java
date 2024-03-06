import Utilities.BaseDriver;
import Utilities.MyMethods;
import org.junit.Assert;
import org.junit.Test;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class US_102 extends BaseDriver {
    @Test
    public void TC_102() throws AWTException {
        Robot robot = new Robot();
        driver.get("https://www.lidl.com/");
        List<WebElement> joinButton= driver.findElements(By.cssSelector("button[aria-label='okay']"));
        if (!joinButton.isEmpty())
            joinButton.get(0).click();
        List<WebElement> cookies = driver.findElements(By.xpath("//button[text()='accept cookies']"));
        if (!cookies.isEmpty())
            wait.until(ExpectedConditions.elementToBeClickable(cookies.get(0))).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated((By.cssSelector("[data-test='signIn']")))).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a[data-test='joinButton']"))).click();
        Assert.assertEquals("hatali site", "https://www.lidl.com/register", driver.getCurrentUrl());
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("input[id='input2']"))).sendKeys("Test");
        driver.findElement(By.cssSelector("input[id='input3']")).sendKeys("Nomads");
        driver.findElement(By.cssSelector("input[id='input4']")).sendKeys("testnomads01@gmail.com");
        driver.findElement(By.cssSelector("input[id='input5']")).sendKeys("testnomads01@gmail.com");
        driver.findElement(By.cssSelector("input[id='input6']")).sendKeys("Nomad07++");
        driver.findElement(By.cssSelector("input[id='input7']")).sendKeys("4213213240");
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

        driver.findElement(By.cssSelector("li[tabindex='-1']")).click();
        MyMethods.myWait(2);

        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_T);
        robot.keyRelease(KeyEvent.VK_T);
        robot.keyRelease(KeyEvent.VK_CONTROL);
        MyMethods.myWait(2);

        Set<String> x = driver.getWindowHandles();
        Iterator iterator = x.iterator();
//        String ilkSayfa = iterator.next().toString();
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
        Assert.assertEquals("duplicated Number", "This phone number is already being used, please use another one.", number2.getText());

        //CASE 7
        driver.findElement(By.cssSelector("input[id='input6']")).sendKeys("0111");
        WebElement zipCode = wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("[id='input6-helper-text']"))));
        Assert.assertEquals("duplicated zip number", "Enter a valid zipcode.", zipCode.getText());

        //CASE 9
        WebElement birth = driver.findElement(By.cssSelector("input[id='date-picker-dialog']"));
        birth.sendKeys("05011922");
        WebElement birthdate = wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("[id='date-picker-dialog-helper-text']"))));
        Assert.assertEquals("overaged", "Age must be less than 100.", birthdate.getText());
        birth.sendKeys(Keys.CONTROL + "a");
        birth.sendKeys(Keys.DELETE);

        //CASE 11
        MyMethods.myWait(2);
        driver.findElement(By.cssSelector("input[id='date-picker-dialog']")).sendKeys("10102022");
        MyMethods.myWait(3);
        WebElement underAge = wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("[id='date-picker-dialog-helper-text']"))));
        Assert.assertEquals("underage registered", "You must be at least 13 years old.", underAge.getText());

        waitAndQuit();
    }
}