import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

public class Issue9528 {

  public static void main(String[] args) {
    WebDriver remoteWebDriver = new RemoteWebDriver(new ChromeOptions());
    try {
      Dimension defaultWindowSize = remoteWebDriver.manage().window().getSize();
      for (int i = 0; i < 2000; i++)
      {
        System.out.println(i+1);

        /*
          Starting python server with
          -> python3 -m http.server 8080
          at the root of the issue project
         */
        remoteWebDriver.get("http://192.168.1.27:8080/test.html");
        assert "Test".equalsIgnoreCase(remoteWebDriver.getTitle());

        // uncommenting these lines below sometimes causes: org.openqa.selenium.WebDriverException: null value in entry: message=null
        // remoteWebDriver.findElement(By.tagName("input")).sendKeys("some text");
        // assertEquals("some text", ((JavascriptExecutor)remoteWebDriver).executeScript("return document.getElementById('name').value"));

        ((JavascriptExecutor) remoteWebDriver).executeScript("return document.getElementById('name').value");
        ((JavascriptExecutor) remoteWebDriver).executeScript("alert(1)");
        remoteWebDriver.switchTo().alert().accept();
        remoteWebDriver.switchTo().defaultContent();

        remoteWebDriver.navigate().refresh();

        remoteWebDriver.switchTo().window(remoteWebDriver.getWindowHandles().iterator().next());

        remoteWebDriver.manage().deleteAllCookies();

        remoteWebDriver.navigate().to("about:blank");

        remoteWebDriver.manage().window().setSize(defaultWindowSize);
      }
    } finally {
      remoteWebDriver.quit();
    }
  }

}
