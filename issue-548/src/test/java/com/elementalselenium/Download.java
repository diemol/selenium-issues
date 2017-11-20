package com.elementalselenium;

import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.File;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;

public class Download {

    @SuppressWarnings("ConstantConditions")
    @Test
    public void download() throws Exception {
        File folder = new File("/tmp/downloads");
        URL url = new URL("http://localhost:4444/wd/hub");

        ChromeOptions chromeOptions = new ChromeOptions();
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("download.default_directory", folder.getAbsolutePath());
        prefs.put("profile.default_content_settings.popups", 0);
        chromeOptions.setExperimentalOption("prefs", prefs);
        WebDriver driver = new RemoteWebDriver(url, chromeOptions);

        driver.get("http://the-internet.herokuapp.com/download");
        driver.findElement(By.cssSelector(".example a")).click();

        // Wait 2 seconds to download file
        Thread.sleep(2000);

        System.out.println(folder.getAbsolutePath());
        File[] listOfFiles = folder.listFiles();
        // Make sure the directory is not empty
        assertThat(listOfFiles.length, is(not(0)));
        for (File file : listOfFiles) {
            // Make sure the downloaded file(s) is(are) not empty
            assertThat(file.length(), is(not((long) 0)));
        }

        driver.quit();
    }

}