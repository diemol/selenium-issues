import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.NetworkInterceptor;
import org.openqa.selenium.remote.http.Filter;
import org.openqa.selenium.remote.http.HttpResponse;

public class Issue9941 {

  public static void main (String[] args) throws InterruptedException {
    ChromeDriver driver = new ChromeDriver();
    Filter reportStatusCodes = next -> req -> {
      HttpResponse res = next.execute(req);
      System.out.printf("Request: %s -> Response: %s%n", req, res.getStatus());
      return res;
    };

    try (NetworkInterceptor ignore = new NetworkInterceptor(driver, reportStatusCodes)) {
      driver.get("http://SeleniumHQ.com");
    }

    Thread.sleep(4000);

    driver.quit();
  }

}
