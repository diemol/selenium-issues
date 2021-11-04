import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class Issue1416 {

  public static void main(String[] args) throws MalformedURLException, InterruptedException {
    FirefoxOptions options = new FirefoxOptions();
    options.setUnhandledPromptBehaviour(UnexpectedAlertBehaviour.ACCEPT);
    options.addPreference("intl.accept_languages", "nl-NL, nl");

    WebDriver driver = new RemoteWebDriver(new URL("http://localhost:4444"), options);

    try {
      driver.get("https://www.google.com");

      // Check the browser, it should be in de-DE
      Thread.sleep(5000);
    } finally {
      driver.quit();
    }

  }

}
