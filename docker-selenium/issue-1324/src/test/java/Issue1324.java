import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.LocalFileDetector;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

public class Issue1324 {

    public static void main(String[] args) throws MalformedURLException, InterruptedException {
        File file = new File("c:\\files\\test.txt");

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--disable-dev-shm-usage");
        RemoteWebDriver driver = new RemoteWebDriver(new URL("http://localhost:4444"), chromeOptions);
        driver.setFileDetector(new LocalFileDetector());
        driver.get("https://ps.uci.edu/~franklin/doc/file_upload.html");
        driver.findElement(By.name("userfile")).sendKeys(file.getAbsolutePath());
        Thread.sleep(10000);
        driver.quit();

    }

}
