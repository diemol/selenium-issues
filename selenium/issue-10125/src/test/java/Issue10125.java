import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverLogLevel;
import org.openqa.selenium.chrome.ChromeOptions;

public class Issue10125 {
  public static void main(String[] args) {
    ChromeOptions options = new ChromeOptions();
    options.setLogLevel(ChromeDriverLogLevel.DEBUG);
    options.addArguments("--Arg1=1");
    WebDriver driver = new ChromeDriver(options);
    driver.get("https://google.com/");
    driver.quit();
  }
}
