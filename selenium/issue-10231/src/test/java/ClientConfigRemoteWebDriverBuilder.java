import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.HttpCommandExecutor;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.http.ClientConfig;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class ClientConfigRemoteWebDriverBuilder {

  public static void main (String[] args) throws MalformedURLException {
    ClientConfig config = ClientConfig.defaultConfig()
      .baseUrl(new URL("http://192.168.1.11:4444/wd/hub"))
      .readTimeout(Duration.ofMinutes(1));
    HttpCommandExecutor httpCommandExecutor = new HttpCommandExecutor(config);
    WebDriver driver = new RemoteWebDriver(httpCommandExecutor, new ChromeOptions());
    driver.get("https://selenium.dev");
    driver.quit();
  }

}
