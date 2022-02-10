import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Issue {

  public static void main (String[] args) {
    ChromeDriver driver = new ChromeDriver();
    driver.get("https://the-internet.herokuapp.com/key_presses");
    WebElement element = driver.findElement(By.id("target"));

    element.sendKeys("abcd" + Keys.MULTIPLY + Keys.SUBTRACT + Keys.ADD +
                     Keys.DECIMAL + Keys.SEPARATOR + Keys.NUMPAD0 + Keys.NUMPAD9 +
                     Keys.ADD + Keys.SEMICOLON + Keys.EQUALS + Keys.DIVIDE +
                     Keys.NUMPAD3 + "abcd");
    String value = element.getAttribute("value");
    driver.quit();
    if (value.equals("abcd*-+.,09+;=/3abcd")) {
      System.exit(0);
    }
    System.exit(1);
  }


}
