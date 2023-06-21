
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

public class SlackCodeFromSeleniumHQ {

    public static void main(String... args) throws InterruptedException {
        EdgeOptions options = new EdgeOptions().addArguments("debuggerAddress","localhost:9222");
        System.out.println(options);
        EdgeDriver driver = new EdgeDriver(options);
        driver.get("https://selenium.dev");
        Thread.sleep(5000);
        driver.quit();
    }


}
