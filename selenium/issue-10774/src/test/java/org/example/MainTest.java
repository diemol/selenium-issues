package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class MainTest {

  @Test
  public void issue10774Test() {
    WebDriverManager.chromedriver().setup();
    WebDriver driver = new ChromeDriver();

    driver.get("http://watir.com/examples/shadow_dom.html");

    WebElement shadowHost = driver.findElement(By.cssSelector("#shadow_host"));
    SearchContext shadowRoot = shadowHost.getShadowRoot();
    WebElement shadowContent = shadowRoot.findElement(By.cssSelector("#shadow_content"));

    Assertions.assertEquals("some text", shadowContent.getText());
    driver.quit();
  }
}
