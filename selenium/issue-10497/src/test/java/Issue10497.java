import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class Issue10497 {
    public static void main (String[] args) {
        WebDriverManager.iedriver().arch32().setup();
        WebDriver driver = new InternetExplorerDriver();
        driver.get("https://selenium.dev/");
        driver.manage().window().maximize();
        driver.quit();
    }
}
