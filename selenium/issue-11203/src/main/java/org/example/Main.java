package org.example;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class Main {

  public static void main (String[] args) throws InterruptedException, MalformedURLException {
    Map<String, Object> prefs = new HashMap<>();
    prefs.put("intl.accept_languages", "de");
    ChromeOptions chromeOptions = new ChromeOptions();
    chromeOptions.setExperimentalOption("prefs", prefs);
    WebDriver driver = RemoteWebDriver.builder()
      .addAlternative(chromeOptions)
      .address(new URL("http://localhost:4444")).build();
    driver.get("https://www.google.com");
    Thread.sleep(1000 * 10);
    driver.quit();
  }
}
