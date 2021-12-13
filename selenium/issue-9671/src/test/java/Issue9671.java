import org.openqa.selenium.OutputType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Issue9671 {

  public static void main (String[] args) {
    Logger logger = Logger.getLogger(RemoteWebDriver.class.getName());
    logger.setLevel(Level.FINER);

    for (Handler handler : Logger.getLogger("").getHandlers()) {
      handler.setLevel(Level.FINER);
    }
    ChromeDriver driver = new ChromeDriver();

    driver.navigate().to("https://google.com");
    driver.getScreenshotAs(OutputType.BYTES);
    driver.quit();
  }

}
