import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.List;

public class ExecutingAsyncJavascript {
  public static void main (String[] args) {
    ChromeOptions options = new ChromeOptions();
    options.setBinary("/Applications/Google Chrome.app/Contents/MacOS/Google Chrome");
    WebDriver driver = new ChromeDriver(options);
    try {
      driver.get("https://www.selenium.dev/selenium/web/ajaxy_page.html");
      JavascriptExecutor executor = (JavascriptExecutor) driver;

      Object result = executor.executeAsyncScript(
          "arguments[arguments.length - 1]([document.body, document.body]);");
      assert result != null;
      assert result instanceof List;

      List<?> list = (List<?>) result;
      assert list.size() == 2;
      assert list.get(0) instanceof WebElement;
      assert list.get(1) instanceof WebElement;
      WebElement webElement = (WebElement) list.get(0);
      assert webElement.getTagName().equalsIgnoreCase("body");
      assert webElement.equals(list.get(1));
    } finally {
      driver.quit();
    }
  }
}
