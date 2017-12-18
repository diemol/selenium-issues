import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.net.URL;

public class ChromeTest {
    @SuppressWarnings({"ConstantConditions", "ResultOfMethodCallIgnored"})
    @Test
    public void chromeTest() throws Exception {
        URL url = new URL("http://localhost:4444/wd/hub");

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--window-size=1280,1024");

        DesiredCapabilities desiredCapabilities = DesiredCapabilities.chrome();
        desiredCapabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
        WebDriver driver = new RemoteWebDriver(url, desiredCapabilities);

        driver.get("http://the-internet.herokuapp.com");

        // 10 seconds to check inside the container via vnc
        Thread.sleep(1000 * 10);

        Assert.assertEquals(driver.getTitle(), "The Internet");

        driver.quit();
    }

}
