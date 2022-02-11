import org.openqa.selenium.UsernameAndPassword;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.http.ClientConfig;

import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.Proxy;
import java.net.URL;

public class Issue10231 {

  public static void main (String[] args) throws MalformedURLException {
    // This will work with Selenium 4.1.3 and above
    ChromeOptions browserOptions = new ChromeOptions();
    // Proxy started with https://github.com/diemol/squid-with-basic-auth
    Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("locahost", 3128));
    ClientConfig config = ClientConfig.defaultConfig()
      .baseUrl(new URL("http://192.168.1.7:4444"))
      .authenticateAs(new UsernameAndPassword("docker-proxy", "selenium"))
      .proxy(proxy);
    WebDriver driver = RemoteWebDriver.builder()
      .oneOf(browserOptions)
      .config(config)
      .build();
    driver.get("https://selenium.dev");
    driver.quit();
  }

}
