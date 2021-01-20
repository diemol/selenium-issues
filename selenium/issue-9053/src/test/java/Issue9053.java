import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.List;
import java.util.Set;

public class Issue9053 {

  public static void main(String[] args) {
    System.setProperty("webdriver.ie.driver", "D:\\IEDriverServer_Win32_3.141.59\\IEDriverServer(1).exe");
    // login
    WebDriver driver = new InternetExplorerDriver();
    driver.get("http://wssq.sbj.cnipa.gov.cn:9080/tmsve/wssqsy_getloginPin.xhtml#");
    driver.manage().window().maximize();
    // Input pin
    WebElement input = driver.findElement(By.id("pin"));
    input.sendKeys("123123");
    // Click login
    driver.findElement(By.id("pinWord")).click();
    try {
      Thread.sleep(2000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    WebElement next = null;
    while (next == null) {
      next = driver.findElement(By.xpath("//div[@class='pop-foot']/input[@value='下一页']"));
    }
    next.click();
    System.out.println(next.getText());
    try {
      Thread.sleep(3000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    WebElement end = null;
    while (end == null) {
      end = driver.findElement(By.xpath("//div[@class='pop-foot']/input[@class='pop-ok pop-close']"));
    }
    end.click();
    System.out.println(end.getText());
    try {
      Thread.sleep(2000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    WebElement findElement = null;
    System.err.println("查找注册申请");
    // If no tag is found, keep repeating the previous action
    while (findElement == null) {
      driver.findElement(By.linkText("商标注册申请")).click();

      findElement = driver.findElement(By.linkText("注册申请"));
      System.err.println("循环商标注册申请");
    }
    findElement.click();
    driver.switchTo().frame("myframe");
    /*
     * Go to the first page
     * Gets the object of the first page
     * Select legal person/natural person
     *
     */
    WebElement el = driver.findElement(By.name("tmzcr.appTypeId"));
    Select sel = new Select(el);
    sel.selectByValue("100012000000000002");
    el = driver.findElement(By.name("tmzcr.appGjdq"));
    Select sel2 = new Select(el);
    sel2.selectByValue("100011000000000001");
    List<WebElement> findElements = driver.findElements(By.className("zhuceBtn"));
    findElements.get(0).click();
    findElements.get(2).click();
    findElements.get(4).click();
    findElements.get(6).click();
    findElements.get(8).click();
    for(int i=1;i<=45;i++) {
      String search_handle = driver.getWindowHandle();
      driver.findElement(By.linkText("【点击添加商品/服务项目】")).click();
      Set<String> handles = driver.getWindowHandles();
      /*
       * This is where the code reported an error
       */
      for (String handle : handles) {
        if (handle.equals(search_handle) == false) {
          driver.switchTo().window(handle);
        }
      }
    }
  }

}
