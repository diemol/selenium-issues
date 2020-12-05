import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.concurrent.TimeUnit;

public class Issue8823 {
    public static void main(String[] args){
        ChromeOptions options = new ChromeOptions();
        options.setHeadless(true);
        ChromeDriver driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        try {
            // Do some actions with the driver...
            driver.navigate().to("https://mail.yandex.com");
            driver.findElementByXPath("//div[4]/a[. = 'Log in']").click();
            WebElement login = driver.findElementByXPath("//button[. = 'Log in']");
            login.click();
            TimeUnit.SECONDS.sleep(2);
            driver.findElementByCssSelector("#passp-field-login").sendKeys("tzah4748");
            login.click();
            TimeUnit.SECONDS.sleep(2);
            WebElement password = driver.findElementByCssSelector("#passp-field-passwd");
            password.sendKeys("aaa123456789");
            WebElement login2 = driver.findElementByXPath("//*[@id=\"root\"]/div/div/div[2]/div/div/div[2]/div[3]/div/div/div/form/div[3]/button");
            login2.click();
            // AFTER THIS LINE - on chrome headless any command to the driver will reveal the bug.
            password.sendKeys("zaq123edc");
        } catch (Throwable e) {
            e.printStackTrace();
        } finally {
            driver.quit();
        }
        System.exit(0);
    }
}
