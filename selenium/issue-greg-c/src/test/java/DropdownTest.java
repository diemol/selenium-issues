import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class DropdownTest {

  public static void main (String[] args) {
    WebDriver driver = new ChromeDriver();
    driver.get("http://the-internet.herokuapp.com/dropdown");
    Select selectList = new Select(driver.findElement(By.id("dropdown")));
    selectList.selectByVisibleText("Option 1");
    String selectedOption = selectList.getFirstSelectedOption().getText();
    driver.quit();

    if ("Option 1".equalsIgnoreCase(selectedOption)) {
      System.out.println("It works!");
    } else {
      System.out.println("It didn't work.");
    }
  }

}
