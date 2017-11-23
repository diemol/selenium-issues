package com.elementalselenium;

import com.google.common.io.*;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Test;

import java.io.File;
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

        WebDriver augmentedDriver = new Augmenter().augment(driver);
        File screenshot = ((TakesScreenshot)augmentedDriver)
                .getScreenshotAs(OutputType.FILE);

        File savedScreenshot = new File("/tmp/images/screenshot.png");
        Files.copy(screenshot, savedScreenshot);

        driver.quit();
    }

}