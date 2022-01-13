import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariOptions;

import java.net.MalformedURLException;
import java.net.URL;

public class Issue7790 {

  public static void main (String[] args) throws MalformedURLException {
    SafariOptions safariOptions = new SafariOptions();
    safariOptions.setAutomaticInspection(true);
    safariOptions.setAutomaticProfiling(true);
    RemoteWebDriver webDriver =  new RemoteWebDriver(new URL("http://localhost:4444"), safariOptions);
    webDriver.get("https://www.google.com");
    webDriver.quit();
  }

}
