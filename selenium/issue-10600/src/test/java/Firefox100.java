import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Firefox100 {

  public static void main (String[] args) {
    FirefoxDriver driver = new FirefoxDriver();
    DevTools devTools = driver.getDevTools();
    driver.quit();
  }

}
