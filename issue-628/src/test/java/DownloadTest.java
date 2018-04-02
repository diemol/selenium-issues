import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class DownloadTest {

    @SuppressWarnings({"ConstantConditions", "ResultOfMethodCallIgnored"})
    @Test
    public void download() throws Exception {
        // This is the path inside the container
        File folder = new File("/tmp/downloads");
        URL url = new URL("http://selenium-hub:4444/wd/hub");

        ChromeOptions chromeOptions = new ChromeOptions();
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("download.default_directory", folder.getAbsolutePath());
        prefs.put("download.prompt_for_download", "false");
        chromeOptions.setExperimentalOption("prefs", prefs);

        DesiredCapabilities desiredCapabilities = new DesiredCapabilities(chromeOptions);
        WebDriver driver = new RemoteWebDriver(url, desiredCapabilities);

        driver.get("http://the-internet.herokuapp.com/download");
        driver.findElement(By.cssSelector(".example a")).click();

        // Wait 2 seconds to download file
        Thread.sleep(2000);

        System.out.println(folder.getAbsolutePath());
        File[] listOfFiles = folder.listFiles();
        // Make sure the directory is not empty
        Assert.assertNotEquals(listOfFiles.length, 0);
        for (File file : listOfFiles) {
            // Make sure the downloaded file(s) is(are) not empty
            Assert.assertNotEquals(file.length(), 0);
        }

        driver.quit();
    }

}