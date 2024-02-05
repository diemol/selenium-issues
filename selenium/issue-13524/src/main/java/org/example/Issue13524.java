package org.example;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class Issue13524 {

  public static void main(String[] args) {
    ChromeOptions options = new ChromeOptions();
    options.addArguments("--no-sandbox");// 禁用沙箱
    options.addArguments("--disable-dev-shm-usage");// 禁用开发者shm
    options.addArguments("--headless"); // 无头浏览器，这样不会打开浏览器窗口

    WebDriver webDriver = new ChromeDriver(options);
    webDriver.get("https://www.baidu.com/");
    webDriver.getPageSource();
    webDriver.close();
    webDriver.quit();
  }

}
