import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.HasDevTools;
import org.openqa.selenium.devtools.v96.browser.Browser;
import org.openqa.selenium.devtools.v96.browser.model.Bounds;
import org.openqa.selenium.devtools.v96.browser.model.WindowID;
import org.openqa.selenium.devtools.v96.browser.model.WindowState;
import org.openqa.selenium.devtools.v96.log.Log;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Optional;

public class Issue9803 {

  public static void main (String[] args) throws MalformedURLException, InterruptedException {
    WebDriver driver = new RemoteWebDriver(new URL("http://localhost:4444"), new ChromeOptions());
    driver = new Augmenter().augment(driver);
    driver.get("https://www.duckduckgo.com");
    DevTools devTools = ((HasDevTools) driver).getDevTools();
    devTools.createSession();

    devTools.send(Log.enable());
    devTools.send(Browser.setWindowBounds(new WindowID(1), new Bounds(
      Optional.of(20),
      Optional.of(20),
      Optional.of(20),
      Optional.of(20),
      Optional.of(WindowState.NORMAL))));

    driver.quit();
  }

}
