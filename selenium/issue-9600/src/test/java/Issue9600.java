import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class Issue9600 {

  public static void main(String[] args) throws MalformedURLException, InterruptedException {
//    InternetExplorerOptions ieOptions = new InternetExplorerOptions();
//    WebDriver driver = new RemoteWebDriver(new URL("http://localhost:4444"), ieOptions);
    InternetExplorerDriver driver = new InternetExplorerDriver();
    driver.get("http://www.google.com");
    Thread.sleep(10000);
    driver.quit();
  }

}
