import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class ChromeBiDiIssue {

    public static void main(String... args) {
        ChromeOptions options = new ChromeOptions();
        options.addArguments(
                "disable-extensions",
                "disable-infobars",
                "disable-breakpad",
                "disable-dev-shm-usage",
                "no-sandbox");

        Map<String, Object> prefs = new HashMap<>();
        prefs.put("exit_type", "None");
        prefs.put("exited_cleanly", true);
        options.setExperimentalOption("prefs", prefs);

        options.setCapability("webSocketUrl", true);

        ChromeDriver driver = new ChromeDriver(options);
        driver.get("https://www.selenium.dev/selenium/web/xhtmlTest.html");

        String original = driver.getWindowHandle();
        driver.findElement(By.name("windowOne")).click();
        driver.switchTo().window(original);
        driver.findElement(By.name("windowTwo")).click();

        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(windowHandleCountToBeGreaterThan(2));

        Set<String> allWindowHandles = driver.getWindowHandles();

        // There should be three windows. We should also see each of the window titles at least once.
        Set<String> allWindowTitles =
                allWindowHandles.stream()
                        .map(
                                handle -> {
                                    driver.switchTo().window(handle);
                                    return driver.getTitle();
                                })
                        .collect(Collectors.toSet());

        assert allWindowHandles.size() == 3;
        assert allWindowTitles.size() == 3;
    }

    public static ExpectedCondition<Set<String>> windowHandleCountToBeGreaterThan(final int count) {
        return driver -> {
            Set<String> handles = driver.getWindowHandles();
            return handles.size() > count ? handles : null;
        };
    }

}
