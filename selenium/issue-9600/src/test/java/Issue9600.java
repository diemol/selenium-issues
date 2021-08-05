import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class Issue9600 {

  public static void main(String[] args) throws MalformedURLException, InterruptedException {
    InternetExplorerOptions ieOptions = new InternetExplorerOptions();
//    ChromeOptions ieOptions = new ChromeOptions();
    WebDriver driver = new RemoteWebDriver(new URL("http://192.168.1.24:4444"), ieOptions);
    driver.get("http://www.google.com");
    Thread.sleep(10000);
    driver.quit();
  }

}
