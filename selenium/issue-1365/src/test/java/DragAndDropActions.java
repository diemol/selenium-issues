import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

import java.util.concurrent.TimeUnit;

public class DragAndDropActions {

	@Test
	public void testDragAndDrop() {
		FirefoxDriver driver = new FirefoxDriver();
		try {
			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
			driver.get("http://www.dhtmlx.com/docs/products/dhtmlxTree/");

			// Selecting the element that can be dragged, all the others are below the tree
			WebElement fromElement = driver.findElement(By.xpath("//*[text() = 'Learning DHTMLX Suite UI']"));
			// There are 2 <ul> with the 'dhx_widget' class, we need the 2nd one.
			WebElement toElement = driver.findElements(By.className("dhx_widget")).get(1);

			Actions actions = new Actions(driver);
			actions.dragAndDrop(fromElement, toElement).build().perform();
			Thread.sleep(2000);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			driver.quit();
		}
	}
}
