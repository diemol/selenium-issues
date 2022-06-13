import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.HashMap;
import java.util.Map;

public class Issue10448 {

  public static void main (String[] args) {
    Map<String, Object> chromeOptions = new HashMap<>();
    chromeOptions.put("w3c", false);
    ChromeOptions options = new ChromeOptions();
    options.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
    WebDriver driver = new ChromeDriver(options);
    driver.get("https://selenium.dev");
    driver.quit();
  }

}
