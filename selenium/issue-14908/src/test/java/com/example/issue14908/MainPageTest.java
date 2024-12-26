package com.example.issue14908;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class MainPageTest {
    @Test
    public void issue14908() throws MalformedURLException {
      // Set Selenium Grid URL
      String seleniumUrl = "http://localhost:4444";
      // Configure ChromeOptions
      ChromeOptions options = new ChromeOptions();
      options.enableBiDi();

      // Create WebDriver
      WebDriver driver = new RemoteWebDriver(new URL(seleniumUrl), options);
      driver = new Augmenter().augment(driver);

      // Perform a simple test
      driver.get("https://example.com");
      System.out.println("Page Title: " + driver.getTitle());

      // Quit WebDriver
      driver.quit();
    }
}
