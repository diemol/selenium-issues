import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.util.HashMap;
import java.util.Map;

public class Issue10661 {

  public static void main (String[] args) {
    FirefoxOptions browserOptions = new FirefoxOptions();

    browserOptions.setPlatformName("Windows 10");
    browserOptions.setBrowserVersion("latest");

    Map<String, Object> sauceOptions = new HashMap<>();
    sauceOptions.put("seleniumVersion", "4.0.0");
    sauceOptions.put("username", System.getenv("SAUCE_USERNAME"));
    sauceOptions.put("accessKey", System.getenv("SAUCE_ACCESS_KEY"));

    WebDriver driver = RemoteWebDriver.builder()
      .oneOf(browserOptions)
      .setCapability("sauce:options", sauceOptions)
      .address("https://ondemand.us-west-1.saucelabs.com/wd/hub")
      .address("http://localhost:4444")
      .build();

    driver.quit();
  }

}
