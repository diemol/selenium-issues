import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.chromium.ChromiumDriver;

public class Issue10504 {

  public static void main (String[] args) throws InterruptedException {
    ChromeOptions options = new ChromeOptions();
//    options.addArguments("--incognito"); Uncommenting this makes the script fail.
    ChromiumDriver driver = new ChromeDriver(options);
    driver.get("https://www.selenium.dev");
    driver.switchTo().newWindow(WindowType.TAB);
    Thread.sleep(5000);
    driver.quit();
  }

}
