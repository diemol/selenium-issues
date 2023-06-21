package org.example;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {

  public static void main(String[] args) throws MalformedURLException {
//    ChromeOptions options = new ChromeOptions();
//    options.addArguments("--remote-allow-origins=*", "disable-extensions",
//                         "disable-infobars", "disable-breakpad",
//                         "disable-dev-shm-usage", "no-sandbox");
//    WebDriver driver = new RemoteWebDriver(options);
//    driver.get("https://selenium.dev");
//    driver.quit();

    FirefoxOptions options = new FirefoxOptions();
    options.setHeadless(true);
    WebDriver driver = new FirefoxDriver(options);
    driver.get("https://selenium.dev");
    driver.quit();

//    Logger logger = Logger.getLogger(RemoteWebDriver.class.getName());
//    logger.setLevel(Level.FINER);
//
//    for (Handler handler : Logger.getLogger("").getHandlers()) {
//      handler.setLevel(Level.FINER);
//    }
//    ChromeOptions browserOptions = new ChromeOptions();
//    browserOptions.setPlatformName("Windows 11");
//    browserOptions.setBrowserVersion("111");
//    Map<String, Object> sauceOptions = new HashMap<>();
//    sauceOptions.put("username", System.getenv("SAUCE_USERNAME"));
//    sauceOptions.put("accessKey", System.getenv("SAUCE_ACCESS_KEY"));
//    sauceOptions.put("name", "demoTest");
//    browserOptions.setCapability("sauce:options", sauceOptions);
//    URL url = new URL("https://ondemand.us-west-1.saucelabs.com:443/wd/hub");
//    RemoteWebDriver driver = new RemoteWebDriver(url, browserOptions);
//    try {
//      driver.get("https://selenium.dev");
//      String date = (String) executeScript(driver, "return new Date();");
//    } finally {
//      driver.quit();
//    }
  }



  private static Object executeScript(WebDriver driver, String script, Object... args) {
    return ((JavascriptExecutor) driver).executeScript(script, args);
  }
}
