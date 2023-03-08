package org.example;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.GeckoDriverService;

public class Main {

  public static void main(String[] args) {
    try (GeckoDriverService service = new GeckoDriverService
      .Builder()
      .withAllowHosts("localhost")
      .build()) {
      WebDriver driver = new FirefoxDriver(service);
      driver.get("https://selenium.dev");
      driver.quit();
    }


  }
}
