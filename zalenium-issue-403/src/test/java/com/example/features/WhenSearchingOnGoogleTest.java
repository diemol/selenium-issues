package com.example.features;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.Screenshots;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SerenityRunner.class)
public class WhenSearchingOnGoogleTest {

    @Managed
    WebDriver driver;

    @Test
    @Screenshots(forEachAction=true)
    public void shouldInstantiateAWebDriverInstanceForAWebTest() throws InterruptedException {
        driver.get("http://the-internet.herokuapp.com/download");
        driver.findElement(By.cssSelector(".example a")).click();

        // Wait 2 seconds to download file
        Thread.sleep(2000);

        File folder = new File("/tmp/downloads");
        System.out.println(folder.getAbsolutePath());
        File[] listOfFiles = folder.listFiles();
        // Make sure the directory is not empty
        assertThat(listOfFiles.length).isNotEqualTo(0);
        for (File file : listOfFiles) {
            // Make sure the downloaded file(s) is(are) not empty
            assertThat(file.length()).isNotEqualTo(0);
        }
    }
}