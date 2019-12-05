import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.net.URL;

public class ChromeProfileTest {

    @Test
    public void chromeProfile() throws Exception {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("user-data-dir=/tmp/chrome_profiles");

        WebDriver driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), chromeOptions);

        // 10 seconds to see the default page loading
        Thread.sleep(1000 * 10);

        driver.get("http://the-internet.herokuapp.com");

        Assert.assertEquals(driver.getTitle(), "The Internet");

        driver.quit();
    }

}