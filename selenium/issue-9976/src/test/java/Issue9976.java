import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

public class Issue9976 {

  public static void main (String[] args) {
    String gridUrl = "http://localhost:4444";
    WebDriver driver = RemoteWebDriver.builder()
      .oneOf(new ChromeOptions())
      .address(gridUrl)
      .build();
    driver.get("https://google.com");
    driver.quit();
  }

}
