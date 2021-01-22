import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class Issue9083 {

  public static void main(String[] args) {
    WebDriver driver = new InternetExplorerDriver();

    try {
      driver.get("http://wssq.sbj.cnipa.gov.cn:9080/tmsve/sycjwt_getMain.xhtml");
      driver.manage().window().maximize();
      String originalWindow = driver.getWindowHandle();
      driver.findElement(
        By.xpath("//*[@id=\"form1\"]/TABLE/TBODY/TR/TD/TABLE[2]/TBODY/TR[2]/TD[3]/A")).click();
      for (String windowHandle : driver.getWindowHandles()) {
        if (!originalWindow.contentEquals(windowHandle)) {
          driver.switchTo().window(windowHandle);
          break;
        }
        // Sleep added only to show that the popup loaded
        Thread.sleep(3000);
        driver.findElement(By.xpath("//INPUT[@class=\"zhuceBtn\"]")).click();
        System.out.println("Clicked element successfully!");
        // Sleep added only to show that button was clicked
        Thread.sleep(3000);
      }
    } catch (Exception e) {
      System.out.println(e.getMessage());
    } finally {
      driver.quit();
    }

  }

}
