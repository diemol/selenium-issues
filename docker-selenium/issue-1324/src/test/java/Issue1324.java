import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.LocalFileDetector;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Issue1324 {

    public static void main(String[] args) throws MalformedURLException, InterruptedException {
        Path filePath = Paths.get("docker-selenium/issue-1324/test.txt").toAbsolutePath();

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--disable-dev-shm-usage");
        RemoteWebDriver driver = new RemoteWebDriver(new URL("http://localhost:4444"), chromeOptions);
        driver.setFileDetector(new LocalFileDetector());
        driver.get("https://ps.uci.edu/~franklin/doc/file_upload.html");
        driver.findElement(By.name("userfile")).sendKeys(filePath.toString());
        Thread.sleep(10000);
        driver.quit();

    }

}
