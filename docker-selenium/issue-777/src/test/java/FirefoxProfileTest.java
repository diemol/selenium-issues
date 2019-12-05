import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.net.URL;

public class FirefoxProfileTest {

    @Test
    public void firefoxProfile() throws Exception {
        FirefoxOptions firefoxOptions = new FirefoxOptions();
        firefoxOptions.addArguments("-profile", "/tmp/firefox_profiles/iu105fpe.selenium");

        WebDriver driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), firefoxOptions);

        // 10 seconds to see the default page loading
        Thread.sleep(1000 * 10);

        driver.get("http://the-internet.herokuapp.com");

        Assert.assertEquals(driver.getTitle(), "The Internet");

        driver.quit();
    }

}