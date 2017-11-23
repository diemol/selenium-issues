package com.elementalselenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Test;

import java.net.URL;

public class HeavyPageTest {

    @Test
    public void heavyPage() throws Exception {
        URL url = new URL("http://standalone-chrome-debug:4444/wd/hub");

        DesiredCapabilities desiredCapabilities = DesiredCapabilities.chrome();
        WebDriver driver = new RemoteWebDriver(url, desiredCapabilities);

        driver.get("http://dragons.org/");
        driver.findElement(By.xpath("//*[@id='waypoints']/div[2]/span")).click();

        Thread.sleep(1000);

        driver.quit();
    }

}