import org.openqa.selenium.chrome.ChromeDriver;

public class Issue9203 {

  public static void main(String[] args) throws InterruptedException {
    ChromeDriver chromeDriver = new ChromeDriver();
    chromeDriver.get("https://www.selenium.dev");
    Thread.sleep(5000);
    chromeDriver.quit();
  }

}
