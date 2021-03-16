import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.CapabilityType;

public class Issue8711 {

  public static void main(String[] args) throws InterruptedException {
    InternetExplorerOptions options = new InternetExplorerOptions();
    options.setCapability(InternetExplorerDriver.ENABLE_ELEMENT_CACHE_CLEANUP, true);
    options.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
    options.setCapability(InternetExplorerDriver.IGNORE_ZOOM_SETTING, true);
    options.setCapability(InternetExplorerDriver.IE_ENSURE_CLEAN_SESSION, true);
    options.setCapability(CapabilityType.SUPPORTS_JAVASCRIPT, true);

    WebDriver driver = new InternetExplorerDriver(options);
    try {
      System.out.println("open site");
      driver.navigate().to("https://www.bever.nl/p/bike-citizens-finn-telefoonhouder-QBAAC90014.html?colour=4168");
      Thread.sleep(15000);
      System.out.println("close coookie pop up");
      driver.findElement(By.xpath("//button[contains(@class,'cookie-message__button')] | //button[@class='as-a-btn as-a-btn--fill']")).click();
      Thread.sleep(10000);
      System.out.println("click account menu");
      driver.findElement(By.xpath("//button[@data-qa='account']")).click();
      Thread.sleep(10000);
      System.out.println("enter credentials");
      driver.findElement(By.xpath("//input[@id='email']")).sendKeys("yiveco1882@gridmauk.com");
      driver.findElement(By.xpath("//input[@id='Password']")).sendKeys("Password!");
      System.out.println("click login");
      driver.findElement(By.xpath("//button[@data-qa='login_btn']")).click();
      Thread.sleep(10000);
      System.out.println("click add to basket");
      driver.findElement(By.xpath("//button[contains(@class,'a-button--add-to-basket')] | //button[@data-qa='add_to_basket']")).click();
      Thread.sleep(10000);
      System.out.println("click go to basket");
      driver.findElement(By.xpath("//a[contains(@class,'button__confirm')] | //a[contains(@href, 'checkout') and not (contains(@data-qa, 'basket'))]")).click();
      Thread.sleep(10000);
      System.out.println("click checkout");
      driver.findElement(By.xpath("//div[contains(@class,'checkout__divider--top')]//button[contains(@class,'button__order')] | //a[@data-qa='checkout_button']")).click();
      Thread.sleep(15000);
      System.out.println("get cookies");
      driver.manage().getCookies();
    } finally {
      driver.quit();
    }
  }

}
