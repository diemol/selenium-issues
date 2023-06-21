import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class IssueDocker1873 {

  public static void main (String[] args) throws MalformedURLException {
    String url = "https://4444-seleniumhq-dockerseleni-2a6k3r2mxc0.ws-eu100.gitpod.io";
    FirefoxOptions options = new FirefoxOptions();
    options.setPlatformName("linux");

    RemoteWebDriver webDriver = new RemoteWebDriver(new URL(url), options);
    webDriver.get("https://selenium.dev");
    webDriver.quit();
  }

}
