import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Issue8942 {
  public static void main(String[] args) {
    ExecutorService executorService = Executors.newFixedThreadPool(10);
    List<Future<Boolean>> list = new ArrayList<>();
    System.out.println("start");
    for (int i = 1; i <= 100; i++) {
      list.add(executorService.submit(() -> {
        try {
          ChromeOptions chromeOptions = new ChromeOptions();
          chromeOptions.setHeadless(true);
          WebDriver driver = new RemoteWebDriver(new URL("http://localhost:4444"), chromeOptions);
          driver.get("https://www.bing.com");
          Thread.sleep(1000);
          driver.quit();
          return true;
        } catch (Exception e) {
          e.printStackTrace();
          return false;
        }
      }));
    }
    int trueCount = 0;
    int falseCount = 0;
    for (Future<Boolean> future : list) {
      try {
        Boolean result = future.get();
        if (result) {
          trueCount++;
        } else {
          falseCount++;
        }
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
    executorService.shutdown();
    System.out.println("True count " + trueCount);
    System.out.println("False count " + falseCount);
    System.exit(0);
  }

}
