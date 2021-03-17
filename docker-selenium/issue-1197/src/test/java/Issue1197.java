import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.LocalFileDetector;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class Issue1197 {

  /*
    First start the container - docker run --rm -p 4444:4444 -p 6900:5900 -v /dev/shm:/dev/shm selenium/standalone-chrome:4.0.0-beta-2-prerelease-20210315
   */
  private static final String CHROME_CONTAINER_URL="http://localhost:4444/wd/hub";
  private static final String DOMAIN_URL="https://maven.apache.org/download.cgi";

  public static void main(String[] args) throws MalformedURLException, InterruptedException {
    ChromeOptions options = new ChromeOptions();
    options.setHeadless(true); // Not working when set to true
    URL url = new URL(CHROME_CONTAINER_URL);
    RemoteWebDriver driver = new RemoteWebDriver(url, options);
    try {
      driver.setFileDetector(new LocalFileDetector());
      driver.get(DOMAIN_URL);
      driver.findElement(By.xpath("//*[@id=\"bodyColumn\"]/section/section[2]/table/tbody/tr[2]/td[2]/a")).click();
      // Wait added just to see the file getting downloaded, you need to add a proper way to assert this in your tests
      Thread.sleep(20000);
    } finally {
      driver.quit();
    }
  }

}
