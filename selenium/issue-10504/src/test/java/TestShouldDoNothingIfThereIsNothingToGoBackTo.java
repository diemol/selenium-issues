import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverLogLevel;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Set;

import static org.openqa.selenium.support.ui.ExpectedConditions.not;
import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

public class TestShouldDoNothingIfThereIsNothingToGoBackTo {

  public static void main (String[] args) {
    System.setProperty("webdriver.chrome.logfile", "/tmp/chromedriver_log.txt");
    ChromeOptions options = new ChromeOptions();
    options.setLogLevel(ChromeDriverLogLevel.DEBUG);
    ChromeDriver driver = new ChromeDriver(options);
    Set<String> currentWindowHandles = driver.getWindowHandles();
    ((JavascriptExecutor) driver).executeScript(
      "window.open('https://selenium.dev', 'newWindow')");
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    wait.until(newWindowIsOpened(currentWindowHandles));
    driver.switchTo().window("newWindow");
    wait.until(titleIs("Selenium"));
    String originalTitle = driver.getTitle();
    driver.get("https://www.selenium.dev/documentation/");
    wait.until(not(titleIs(originalTitle)));
    driver.navigate().back();
    wait.until(titleIs(originalTitle));
    driver.navigate().back(); // Nothing to go back to, must stay.
    wait.until(titleIs(originalTitle));
  }

  public static ExpectedCondition<String> newWindowIsOpened(final Set<String> originalHandles) {
    return driver -> driver.getWindowHandles().stream()
      .filter(handle -> ! originalHandles.contains(handle)).findFirst().orElse(null);
  }

}
