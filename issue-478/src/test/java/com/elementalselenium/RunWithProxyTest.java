package com.elementalselenium;

import com.google.gson.JsonObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Test;

import java.net.URL;

public class RunWithProxyTest {

    @Test
    public void runWithProxy() throws Exception {
        URL url = new URL("http://standalone-firefox-debug:4444/wd/hub");

        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("httpProxy", "54.36.182.96:3128");
        jsonObject.addProperty("sslProxy", "54.36.182.96:3128");
        jsonObject.addProperty("proxyType", "manual");
        // jsonObject.addProperty("noProxy", "localhost"); => This is what is currently failing

        DesiredCapabilities desiredCapabilities = DesiredCapabilities.firefox();
        desiredCapabilities.setCapability(CapabilityType.PROXY, jsonObject);
        WebDriver driver = new RemoteWebDriver(url, desiredCapabilities);

        driver.get("https://api.ipify.org/?format=text");

        // Giving you enough time to go to the container through VNC and check the IP
        Thread.sleep(30000);

        driver.quit();
    }

}