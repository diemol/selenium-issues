import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class Issue10175Cli {

  public static void main (String[] args) throws MalformedURLException {
    ChromeOptions options = new ChromeOptions();
    options.setCapability("gsg:customcap", false);
    WebDriver driver = new RemoteWebDriver(new URL("http://localhost:4444"), options);
    driver.get("https://selenium.dev");
    driver.quit();
  }

}
