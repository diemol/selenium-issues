import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.Set;

public class Issue9040 {

  public static void main(String[] args) throws InterruptedException {
    WebDriver driver = new FirefoxDriver();
//    WebDriver driver = new ChromeDriver();

    try {
      String path = Issue9040.class.getClassLoader().getResource("trail.html").getPath();
      driver.get("file://" + path);

      WebElement visit = driver.findElement(By.xpath("//a[@id='newLink']"));
      Set<String> originalWindowHandles = driver.getWindowHandles();

      visit.click();
      System.out.println(driver.getCurrentUrl());

      Set<String> updatedWindowHandles = driver.getWindowHandles();

      for(String window: updatedWindowHandles)
      {
        if(!originalWindowHandles.contains(window)){
          driver.switchTo().window(window);
          break;
        }
      }

      System.out.println("Sleeping 5 secs to show the issue clearly");
      Thread.sleep(5000);
      System.out.println("Woke up!");

      Alert alert = driver.switchTo().alert();
      alert.accept();
    } finally {
      driver.quit();
    }
  }
}
