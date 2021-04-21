import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverInfo;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.chromium.ChromiumDriverCommandExecutor;
import org.openqa.selenium.devtools.Command;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.HasDevTools;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.CommandExecutor;
import org.openqa.selenium.remote.HttpCommandExecutor;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.Response;
import org.openqa.selenium.remote.http.ClientConfig;
import org.openqa.selenium.remote.service.DriverCommandExecutor;
import org.openqa.selenium.remote.service.DriverService;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Issue9314 {

  @SuppressWarnings("FieldCanBeLocal")
  private static final String webVitalsSnippet = "var vitalsCLS = 'unset';\n"
                                                 + "function logCLS({value}) {\n"
                                                 + "  \tvitalsCLS = value.toString();\n"
                                                 + "  \tconsole.log('CLS: ' + vitalsCLS);\n"
                                                 + "}\n"
                                                 + "\n"
                                                 + "var vitalsFID = 'unset';\n"
                                                 + "function logFID({value}) {\n"
                                                 + "  \tvitalsFID = value.toString();\n"
                                                 + "  \tconsole.log('FID: ' + vitalsFID);\n"
                                                 + "}\n"
                                                 + "\n"
                                                 + "var vitalsLCP = 'unset';\n"
                                                 + "function logLCP({value}) {\n"
                                                 + "  \tvitalsLCP = value.toString();\n"
                                                 + "  \tconsole.log('LCP: ' + vitalsLCP);\n"
                                                 + "}\n"
                                                 + "\n"
                                                 + "window.onload = function() {\n"
                                                 + "\t\n"
                                                 + "\tvar script = document.createElement('script');\n"
                                                 + "\tscript.src = 'https://unpkg.com/web-vitals';\n"
                                                 + "  \tscript.onload = function() {\n"
                                                 + "  \t\n"
                                                 + "    // When loading `web-vitals` using a classic script, all the public\n"
                                                 + "    // methods can be found on the `webVitals` global namespace.\n"
                                                 + "\twebVitals.getCLS(logCLS, true); \n"
                                                 + "    webVitals.getFID(logFID, true); \n"
                                                 + "    webVitals.getLCP(logLCP, true); \n"
                                                 + "  \n"
                                                 + "  }\n"
                                                 + "\n"
                                                 + "  document.body.appendChild(script);\n"
                                                 + "\n"
                                                 + "} ";

  private static final Logger log = Logger.getLogger(Issue9314.class.getName());

  public static void main(String[] args) throws Exception {
    WebDriver driver = createDriver();

    long timer = System.currentTimeMillis();
    try {
      driver.get("https://www.walmart.com");
    } catch (TimeoutException te2) {
      log.warning("Chrome Driver thread timed out: " + (System.currentTimeMillis() - timer) ); // prints approximately 3 minutes, no matter what
    } finally {
      driver.quit();
    }
  }

  private static WebDriver createDriver() throws Exception {

    ChromeOptions options = new ChromeOptions();
//    options.setBinary("/usr/bin/chrome");

    //remove unexepectedalertexceptions
    options.addArguments(
      "--silent",
//      "--headless",
      "--blink-settings=imagesEnabled=false",
      "--disable-blink-features",
      "--disable-blink-features=AutomationControlled",
      "--disable-gpu",
      "--no-sandbox",
      "--window-size=1920,1200",
      "--ignore-certificate-errors",
      "--disable-application-cache");
    options.setCapability(CapabilityType.UNHANDLED_PROMPT_BEHAVIOUR, UnexpectedAlertBehaviour.DISMISS);

    // no downloads
    Map<String, Object> chromePrefs = new HashMap<>();
    chromePrefs.put("download_restrictions", 3);
    options.setExperimentalOption("prefs", chromePrefs);

    LoggingPreferences logPrefs = new LoggingPreferences();
    logPrefs.enable(LogType.PERFORMANCE, Level.ALL);
    options.setCapability( "goog:loggingPrefs", logPrefs );
    options.setPageLoadStrategy(PageLoadStrategy.EAGER);

//    ChromeDriverService.Builder builder = new ChromeDriverService.Builder()
//      .withSilent(true)
//      .usingPort(4444);
//    ChromeDriverService chromeDriverService = builder.build();
//    chromeDriverService.sendOutputTo(new FileOutputStream("/dev/null"));
//    chromeDriverService.start();

//    WebDriver driver = RemoteWebDriver
//      .builder()
//      .config(ClientConfig.defaultConfig()
//                .connectionTimeout(Duration.ofMinutes(10))
//                .readTimeout(Duration.ofMinutes(10)))
////      .withDriverService(chromeDriverService)
//      .address(new URL("http://localhost:4444"))
//      .oneOf(options)
//      .build();
//    driver = new Augmenter().augment(driver);

    WebDriver driver = new RemoteWebDriver(options);
    driver = new Augmenter().augment(driver);

    try (DevTools devTools = ((HasDevTools) driver).getDevTools()) {
      devTools.createSessionIfThereIsNotOne();

      // Inject Core Web Vitals scripts
      Map<String, Object> parameters = new HashMap<>();
      parameters.put("source", webVitalsSnippet);
      Command<Object> injectCommand = new Command<>("Page.addScriptToEvaluateOnNewDocument", parameters);
      devTools.send(injectCommand);

      // Stop any unwanted downloading of *.pdf *.crdownload et al. files
      parameters = new HashMap<>();
      parameters.put("behavior","deny");
      Command<Object> downloadCommand = new Command<>("Browser.setDownloadBehavior", parameters);
      devTools.send(downloadCommand);
      driver.manage().timeouts().pageLoadTimeout(Duration.ofMillis(600000));
      driver.manage().timeouts().setScriptTimeout(Duration.ofMillis(5000));
    } finally {
      driver.quit();
    }
    // Inject Core Web Vitals scripts
//    Map<String, Object> parameters = new HashMap<>();
//    parameters.put("source", webVitalsSnippet);
//    ((ChromeDriver) driver).executeCdpCommand("Page.addScriptToEvaluateOnNewDocument", parameters);
//
//    // Stop any unwanted downloading of *.pdf *.crdownload et al. files
//    parameters = new HashMap<>();
//    parameters.put("behavior","deny");
//    ((ChromeDriver) driver).executeCdpCommand("Browser.setDownloadBehavior", parameters);

//    driver.manage().timeouts().pageLoadTimeout(Duration.ofMillis(600000));
//    driver.manage().timeouts().setScriptTimeout(Duration.ofMillis(5000));

    return driver;

  }
}
