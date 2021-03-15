import org.openqa.selenium.Platform;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class Issue1227 {

  public static void main(String[] args) throws MalformedURLException {
    ChromeOptions options = new ChromeOptions();
    options.setCapability(CapabilityType.BROWSER_NAME, BrowserType.CHROME);
    options.setCapability(CapabilityType.PLATFORM_NAME, Platform.MAC);
    RemoteWebDriver driver = null;
    try {
      driver = new RemoteWebDriver(new URL("http://127.0.0.1:4444/wd/hub"), options);
      driver.get("https://selenium.dev");
    } finally {
      if (driver != null) {
        driver.quit();
      }
    }
  }

}
