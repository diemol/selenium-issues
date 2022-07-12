import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class Issue10859 {

  public static void main (String[] args) throws MalformedURLException {
    String sauce_userid = System.getenv("SAUCE_USERNAME");
    String sauce_accessKey = System.getenv("SAUCE_ACCESS_KEY");

    Proxy proxy = new Proxy();
    ChromeOptions options = new ChromeOptions();
    options.setPlatformName("Windows 10");
    options.setBrowserVersion("latest");
    proxy.setHttpProxy("TestProxy:8080");
    options.setCapability("proxy", proxy);

    Map<String, Object> sauceOptions = new HashMap<String, Object>();

    sauceOptions.put("username", sauce_userid);
    sauceOptions.put("accessKey", sauce_accessKey);
    sauceOptions.put("name", "Selenium 4 Integration with sauce labs");
    options.setCapability("sauce:options", sauceOptions);

    URL url = new URL("https://ondemand.saucelabs.com/wd/hub");

    WebDriver driver = new RemoteWebDriver(url, options);

    driver.get("https://www.google.com");
    System.out.println(driver.getTitle());
    driver.quit();
  }

}
