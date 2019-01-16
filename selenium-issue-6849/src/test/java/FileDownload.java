import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.Test;

import java.util.HashMap;

public class FileDownload {

    @Test
    public void fileDownloadTest() throws InterruptedException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setJavascriptEnabled(true);
        capabilities.setCapability("takesScreenshot", true);
        capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
        capabilities.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);
        final ChromeOptions chromeOptions = new ChromeOptions();
        HashMap<String, Object> chromePrefs = new HashMap<>();
        chromePrefs.put("profile.default_content_settings.popups", 0);
        chromePrefs.put("download.prompt_for_download", false);
        chromePrefs.put("download.directory_upgrade", true);
        chromePrefs.put("download.default_directory", "Downloads");
        chromePrefs.put("plugins.always_open_pdf_externally", true);
        chromeOptions.setExperimentalOption("prefs", chromePrefs);
        capabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);

        WebDriver driver = new ChromeDriver(capabilities);
        driver.get("https://angular.io/guide/quickstart");
        By link1 = By.xpath("//a[@href='generated/zips/cli-quickstart/cli-quickstart.zip']");
        Thread.sleep(5000);
        WebElement element = driver.findElement(link1);
        System.out.println("1");
        element.click();
        System.out.println("2");
        Thread.sleep(5000);
        System.out.println("3");
        By link2 = By.xpath("//a[@href='guide/file-structure']");
        System.out.println("4");
        WebElement element2 = driver.findElement(link2);
        System.out.println("5");
        element2.click();
        System.out.println("6");
        driver.quit();
    }

    @Test
    public void fileDownloadTestWithoutDeprecatedChromeDriver() throws InterruptedException {
        WebDriver driver = null;
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setCapability(CapabilityType.SUPPORTS_JAVASCRIPT, true);
        chromeOptions.setCapability("takesScreenshot", true);
        chromeOptions.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
        chromeOptions.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);
        HashMap<String, Object> chromePrefs = new HashMap<>();
        chromePrefs.put("profile.default_content_settings.popups", 0);
        chromePrefs.put("download.prompt_for_download", false);
        chromePrefs.put("download.directory_upgrade", true);
        chromePrefs.put("download.default_directory", "Downloads");
        chromePrefs.put("plugins.always_open_pdf_externally", true);
        chromeOptions.setExperimentalOption("prefs", chromePrefs);

        try {
            driver = new ChromeDriver(chromeOptions);
            driver.get("https://angular.io/guide/quickstart");
            By link1 = By.xpath("//a[@href='generated/zips/cli-quickstart/cli-quickstart.zip']");
            Thread.sleep(5000);
            WebElement element = driver.findElement(link1);
            System.out.println("1");
            element.click();
            System.out.println("2");
            Thread.sleep(5000);
            System.out.println("3");
            By link2 = By.xpath("//a[@href='guide/file-structure']");
            System.out.println("4");
            WebElement element2 = driver.findElement(link2);
            System.out.println("5");
            element2.click();
            System.out.println("6");
        } finally {
            if (driver != null) {
                driver.quit();
            }
        }

    }

}
