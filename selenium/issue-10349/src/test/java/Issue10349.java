import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class Issue10349 {

  public static void main (String[] args) {
    FirefoxOptions options = new FirefoxOptions();
    options.setHeadless(true);
    FirefoxDriver driver = new FirefoxDriver(options);
    driver.get("https://selenium.dev");
    driver.quit();
  }

}
