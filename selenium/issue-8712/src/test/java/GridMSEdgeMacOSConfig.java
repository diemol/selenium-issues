import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class GridMSEdgeMacOSConfig {
	public static void main(String[] args) throws InterruptedException, MalformedURLException {
//		System.setProperty("webdriver.edge.driver", "/usr/local/bin/msedgedriver");
		WebDriver driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), new EdgeOptions());
		driver.get("https://www.selenium.dev/");
		Thread.sleep(10000);
		driver.quit();
		System.exit(0);
	}
}

/*
	Start Grid 3 with the following commands
	java -jar selenium-server-standalone-3.141.59.jar -role hub
	java -jar -Dwebdriver.edge.driver=/usr/local/bin/msedgedriver selenium-server-standalone-3.141.59.jar -role node -nodeConfig nodeConfig.json
 */
