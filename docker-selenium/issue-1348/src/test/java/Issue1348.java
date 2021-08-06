import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class Issue1348 {

  public static void main(String[] args) throws MalformedURLException {
    WebDriver driver;
    String baseURL = "http://demo.guru99.com/test/guru99home/";
    String nodeURL = "http://localhost:4444/wd/hub";
    DesiredCapabilities capability = DesiredCapabilities.chrome();
    capability.setBrowserName("chrome");
    capability.setPlatform(Platform.LINUX);
    driver = new RemoteWebDriver(new URL(nodeURL), capability);
    try {
      driver.get(baseURL);
      if (driver.getPageSource().contains("MOBILE TESTING")) {
        System.out.println("Mobile Testing Link Found");
      } else {
        System.out.println("Failed: Link not found");
      }
    } finally {
      driver.quit();
    }
  }
}
