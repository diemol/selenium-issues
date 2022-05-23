import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Issue10681 {

  public static void main (String[] args) {
    WebDriver driver = new ChromeDriver();
    driver.get("https://webtrader.justforex.com");
    driver.findElement(By.cssSelector("#login")).sendKeys("sssss");
    driver.quit();
  }

}
