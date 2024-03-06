import Utilities.BaseDriver;
import Utilities.MyMethods;
import org.junit.Assert;
import org.junit.Test;

public class US_101 extends BaseDriver {
    @Test
    public void TC_101() {
        driver.get("https://www.lidl.com/");

        MyMethods.myWait(1);
        Assert.assertTrue("hata", driver.getCurrentUrl().equals("https://www.lidl.com/"));
        waitAndQuit();
    }

}