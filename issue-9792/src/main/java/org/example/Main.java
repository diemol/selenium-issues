package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class Main {

  public static void main(String[] args) {
    WebDriverManager.chromedriver().setup();
//    WebDriver webDriver = new ChromeDriver();
    WebDriver webDriver = new RemoteWebDriver(new ChromeOptions());
    try {
      webDriver.get("https://selenium.dev");
      webDriver.findElement(By.id("radioButtonX"));
//      webDriver.quit();
//      webDriver.findElement(By.id("radioButtonX"));
    } finally {
      webDriver.quit();
    }
  }

//  public static void main(String[] args) throws MalformedURLException {
//    ChromeOptions options = new ChromeOptions();
//    options.setPlatformName("Windows 10");
////    options.setBrowserVersion("latest");
//    Map<String, Object> sauceOptions = new HashMap<>();
//    sauceOptions.put("username", System.getenv("SAUCE_USERNAME"));
//    sauceOptions.put("accessKey", System.getenv("SAUCE_ACCESS_KEY"));
//    sauceOptions.put("name", "initialViewSmallScreen[Windows_Chrome_]");
//    sauceOptions.put("screenResolution", "1600x1200");
//    options.setCapability("sauce:options", sauceOptions);
//
//    URL url = new URL("https://ondemand.us-west-1.saucelabs.com/wd/hub");
//    WebDriver webDriver = new RemoteWebDriver(url, options);
//
//    webDriver.get("https://www.saucedemo.com");
//
//    webDriver.quit();
//  }
}
