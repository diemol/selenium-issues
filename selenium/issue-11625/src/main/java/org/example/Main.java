package org.example;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class Main {

  public static void main(String[] args) {
    System.out.println("Hello world!");
  }

  private static void matchNode1() throws MalformedURLException {
    InternetExplorerOptions options = new InternetExplorerOptions();
    options.setCapability("ie.edgechromium", true);
    options.withEdgeExecutablePath("C:\\Program Files (x86)\\Microsoft\\Edge\\Application\\msedge.exe");
    options.setCapability("c:custom-cap", false);
    WebDriver driver = new RemoteWebDriver(new URL("http://hubIPaddress:4444"), options);
    driver.get("https://selenium.dev");
    driver.quit();
  }

  private static void matchNode2() throws MalformedURLException {
    InternetExplorerOptions options = new InternetExplorerOptions();
    options.setCapability("ie.edgechromium", true);
    options.withEdgeExecutablePath("C:\\Program Files (x86)\\Microsoft\\Edge\\Application\\msedge.exe");
    options.setCapability("c:custom-cap", true);
    WebDriver driver = new RemoteWebDriver(new URL("http://hubIPaddress:4444"), options);
    driver.get("https://selenium.dev");
    driver.quit();
  }
}
