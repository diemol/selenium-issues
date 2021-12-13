import java.net.URL;
import java.time.Duration;
import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriverLogLevel;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;

public class Issue10116 {
  public static void main(String[] args) {
    URL hubUrl;
    try {
      hubUrl = new URL("http://localhost:4444/wd/hub");
    } catch (java.net.MalformedURLException e) {
      throw new RuntimeException("Remote WebDriver Hub URL has some error: " + e.getMessage());
    }

    ChromeOptions chromeOptions = new ChromeOptions();
    setOptions(chromeOptions);

    //Initiating your chromedriver
    WebDriver driver = new RemoteWebDriver(hubUrl, chromeOptions);
    //Applied wait time
    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    //maximize window
    driver.manage().window().maximize();

    //Exception in thread "main" org.openqa.selenium.remote.UnreachableBrowserException: Error communicating with the remote browser. It may have died.
    driver.switchTo().defaultContent();
    driver.get("https://www.google.com");

    WebElement agreeButton = driver.findElement(By.xpath("(//button/div[@role='none'])[4]"));
    agreeButton.click();

    driver.findElement(By.cssSelector("input[name='q']")).sendKeys("Selenium");
    new Actions(driver).sendKeys(Keys.ENTER).perform();

    //closing the browser
    driver.quit();
  }

  private static void setOptions(ChromeOptions chromeOptions) {
    addArguments(chromeOptions);
    addOptions(chromeOptions);
    addPreferences(chromeOptions);
  }

  private static void addArguments(ChromeOptions chromeOptions) {
    chromeOptions
      .addArguments("--no-default-browser-check", "--enable-automation", "--disable-infobars", "--disable-component-extensions-with-background-pages", "--disable-extensions", "--start-maximized", "--disable-password-generation");

  }

  private static void addOptions(ChromeOptions chromeOptions) {
    chromeOptions.setLogLevel(ChromeDriverLogLevel.INFO).setAcceptInsecureCerts(true).setUnhandledPromptBehaviour(UnexpectedAlertBehaviour.IGNORE).setPageLoadStrategy(PageLoadStrategy.EAGER);


  }

  private static void addPreferences(ChromeOptions chromeOptions) {
    HashMap<String, Object> chromePreferences = new HashMap<>();
    chromePreferences.put("credentials_enable_service", false);
    chromePreferences.put("profile.password_manager_enabled", false);
    chromeOptions.setExperimentalOption("prefs", chromePreferences);
  }
}
