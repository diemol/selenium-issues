import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Issue1341 {

    // Chrome container previously started with
    // "docker run -d -p 4444:4444 --shm-size="2g" selenium/standalone-chrome:4.0.0-20211102"
    public static void main(String[] args) throws MalformedURLException {
      ChromeOptions chromeOptions = new ChromeOptions();
      chromeOptions.addArguments("test-type");
      chromeOptions.addArguments("--whitelisted-ips");
      chromeOptions.addArguments("disable-sync-synced-notifications");
      chromeOptions.addArguments("--no-sandbox");
      chromeOptions.addArguments("disable-desktop-notifications");
      WebDriver driver = new RemoteWebDriver(new URL("http://localhost:4444"), chromeOptions);
      try {
        driver.get("https://www.bing.com/");
        //download from https://cdnjs.cloudflare.com/ajax/libs/sizzle/2.3.6/sizzle.js
        Path sizzlePath = Paths.get("docker-selenium/issue-1341/sizzle.js").toAbsolutePath();
        File jqueryFile = sizzlePath.toFile();
        getJavascriptExecutor(driver).executeScript(readFileContent(jqueryFile));
        System.out.println(getJavascriptExecutor(driver).executeScript("return Sizzle('html');"));
      } finally {
        driver.quit();
      }
    }

    public static JavascriptExecutor getJavascriptExecutor(WebDriver driver) {
      return (JavascriptExecutor) driver;
    }

    public static String readFileContent(File file) {
      BufferedReader reader = null;
      StringBuffer sbf = new StringBuffer();
      try {
        reader = new BufferedReader(new FileReader(file));
        String tempStr;
        while ((tempStr = reader.readLine()) != null) {
          sbf.append(tempStr);
        }
        reader.close();
        return sbf.toString();
      } catch (IOException e) {
        e.printStackTrace();
      } finally {
        if (reader != null) {
          try {
            reader.close();
          } catch (IOException e1) {
            e1.printStackTrace();
          }
        }
      }
      return sbf.toString();
    }


}
