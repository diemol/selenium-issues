import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class Issue8935 {
  public static void main(String[] args){
    WebDriver driver = new ChromeDriver();
    try {
      driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
      driver.get("https://signin.ebay.com/");
//      driver.findElement(By.xpath(".//*[@id='gh-p-1']/a")).click();
      driver.findElement(By.xpath("//*[@id='create-account-link']")).click();
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      driver.quit();
    }
  }
}
