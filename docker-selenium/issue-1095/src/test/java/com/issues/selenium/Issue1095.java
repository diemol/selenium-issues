package com.issues.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class Issue1095 {

  public static void main (String[] args) throws MalformedURLException, InterruptedException {
    Map<String, Object> chromePrefs = new HashMap<>();
    chromePrefs.put("download.default_directory", "/home/seluser/files");
    chromePrefs.put("download.prompt_for_download", false);
    ChromeOptions options = new ChromeOptions();
    options.setExperimentalOption("prefs", chromePrefs);
    RemoteWebDriver driver = new RemoteWebDriver(new URL("http://localhost:4444"), options);
    driver.get("http://the-internet.herokuapp.com/download");
    driver.findElements(By.cssSelector(".example a")).get(2).click();
    // Wait 2 seconds to download file
    Thread.sleep(2000);
    driver.quit();
  }

}
