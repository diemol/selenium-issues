import com.google.gson.JsonObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Test;

import java.net.URL;

public class RunWithLocalProxyTest {

    @Test
    public void runWithLocalProxyTest() throws Exception {
        URL url = new URL("http://35.158.144.54:4444/wd/hub");

        // TinyProxy is running locally in an Ubuntu machine
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("httpProxy", "localhost:8888");
        jsonObject.addProperty("sslProxy", "localhost:8888");
        jsonObject.addProperty("proxyType", "manual");

        DesiredCapabilities desiredCapabilities = DesiredCapabilities.firefox();
        desiredCapabilities.setCapability(CapabilityType.PROXY, jsonObject);
        WebDriver driver = new RemoteWebDriver(url, desiredCapabilities);

        driver.get("https://api.ipify.org/?format=text");

        // Giving you enough time to go to the container through VNC and check the IP
        Thread.sleep(30000);

        driver.quit();
    }

}
