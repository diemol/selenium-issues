import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Set;

public class Issue9040 {

  public static void main(String[] args) {
//    System.setProperty("webdriver.chrome.driver", "D:/chrome/chromedriver.exe");
    WebDriver driver = new ChromeDriver();
    try {
      driver.get("https://www.w3schools.com/tags/tryit.asp?filename=tryhtml5_ev_onload");

      Alert pageIsOpen = driver.switchTo().alert();
      pageIsOpen.accept();

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

      Alert alert = driver.switchTo().alert();
      alert.accept();
    } finally {
      driver.quit();
    }
  }
}
