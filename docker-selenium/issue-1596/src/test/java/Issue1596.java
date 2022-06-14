import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

public class Issue1596 {
    public static void main (String[] args) {
        WebDriver driver = new RemoteWebDriver(new EdgeOptions());
        driver.get("https://selenium.dev");
        driver.quit();
    }
}
