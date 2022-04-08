import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class Issue10495Test {

    private final int nTests = 2;
    private final ExecutorService executor = Executors.newFixedThreadPool(nTests);

    @Test
    public void concurrentEdgeIEMode() throws ExecutionException, InterruptedException, TimeoutException {
        WebDriverManager.iedriver().arch32().setup();

        CompletableFuture<?>[] futures = new CompletableFuture<?>[nTests];
        for (int i = 0; i < nTests; i++) {
            CompletableFuture<Object> future = new CompletableFuture<>();
            futures[i] = future;
            executor.submit(() -> {
                try {
                    InternetExplorerOptions ieOptions = new InternetExplorerOptions();
                    ieOptions.attachToEdgeChrome();
                    ieOptions.withEdgeExecutablePath("C:\\Program Files (x86)\\Microsoft\\Edge\\Application\\msedge.exe");
                    WebDriver driver = new InternetExplorerDriver(ieOptions);
                    driver.get("http://www.bing.com");
                    WebElement elem = driver.findElement(By.id("sb_form_q"));
                    elem.sendKeys("WebDriver", Keys.RETURN);
                    driver.quit();
                    future.complete(true);
                } catch (Exception e) {
                    future.completeExceptionally(e);
                }
            });
        }
        CompletableFuture.allOf(futures).get(3, TimeUnit.MINUTES);
    }
}
