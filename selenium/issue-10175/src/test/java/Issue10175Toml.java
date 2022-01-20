import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class Issue10175Toml {

  public static void main (String[] args) throws MalformedURLException {
    FirefoxOptions options = new FirefoxOptions();
    options.setCapability("networkname:applicationName", "node_2");
    options.setCapability("nodename:applicationName", "app_2");
    options.setBrowserVersion("96");
    options.setPlatformName("macOS");
    WebDriver driver = new RemoteWebDriver(new URL("http://localhost:4444"), options);
    driver.get("https://selenium.dev");
    driver.quit();
  }

}
