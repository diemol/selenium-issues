import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariOptions;

public class SafariTechPreview {

  public static void main (String[] args) {
    SafariOptions safariOptions = new SafariOptions();
    safariOptions.setUseTechnologyPreview(true);
    WebDriver driver = new RemoteWebDriver(safariOptions);
    driver.navigate().to("https://duckduckgo.com/");
    driver.quit();
  }

}
