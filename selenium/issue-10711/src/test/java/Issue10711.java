import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.Platform;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.CapabilityType;

import java.time.Duration;

public class Issue10711 {

  public static void main (String[] args) {
    WebDriver driver = initializeEdge();
    driver.navigate().to("https://duckduckgo.com/");
    driver.quit();
  }

  static ChromeDriver initializeChrome(){
    ChromeOptions options = new ChromeOptions();
    options.setCapability(CapabilityType.PLATFORM_NAME, Platform.ANY);
    options.setHeadless(false);
    options.addArguments("--start-maximized");
    options.setUnhandledPromptBehaviour(UnexpectedAlertBehaviour.ACCEPT_AND_NOTIFY);
    options.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);
    options.setPageLoadStrategy(PageLoadStrategy.NORMAL); // https://www.skptricks.com/2018/08/timed-out-receiving-message-from-renderer-selenium.html
    options.setPageLoadTimeout(Duration.ofSeconds(30000));
    options.setScriptTimeout(Duration.ofSeconds(30000));
    return new ChromeDriver(options);
  }

  static EdgeDriver initializeEdge(){
    EdgeOptions options = new EdgeOptions();
    options.setCapability(CapabilityType.PLATFORM_NAME, Platform.ANY);
    options.setHeadless(false);
    options.addArguments("--start-maximized");
    options.setUnhandledPromptBehaviour(UnexpectedAlertBehaviour.ACCEPT_AND_NOTIFY);
    options.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);
    options.setPageLoadStrategy(PageLoadStrategy.NORMAL); // https://www.skptricks.com/2018/08/timed-out-receiving-message-from-renderer-selenium.html
    options.setPageLoadTimeout(Duration.ofSeconds(30000));
    options.setScriptTimeout(Duration.ofSeconds(30000));
    return new EdgeDriver(options);
  }

  static FirefoxDriver initializeFirefox(){
    FirefoxOptions options = new FirefoxOptions();
    options.setCapability(CapabilityType.PLATFORM_NAME, Platform.ANY);
    options.setHeadless(false);
    options.addArguments("--start-maximized");
    options.setUnhandledPromptBehaviour(UnexpectedAlertBehaviour.ACCEPT_AND_NOTIFY);
    options.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);
    options.setPageLoadStrategy(PageLoadStrategy.NORMAL); // https://www.skptricks.com/2018/08/timed-out-receiving-message-from-renderer-selenium.html
    options.setPageLoadTimeout(Duration.ofSeconds(30000));
    options.setScriptTimeout(Duration.ofSeconds(30000));
    return new FirefoxDriver(options);
  }
}
