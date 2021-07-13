import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class Issue1306 {

  public static void main(String[] args) throws MalformedURLException {
    WebDriver driver = null;
    try {
      driver = new RemoteWebDriver(new URL("http://localhost:4444"), new EdgeOptions());
      driver.get("https://selenium.dev");
    } finally {
      if (driver != null) {
        driver.quit();
      }
    }
  }

}
