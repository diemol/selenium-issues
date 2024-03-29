import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class Issue10600 {
  private static final String hubUrl = "http://192.168.1.42:4444";

  public static void main(String... args) throws MalformedURLException, InterruptedException {
    WebDriver remoteWebDriver = new RemoteWebDriver(new URL(hubUrl), getIEOptions());
    remoteWebDriver.get("https://google.co.uk");
    Thread.sleep(10000);
    remoteWebDriver.quit();
  }


  public static InternetExplorerOptions getIEOptions() {
    InternetExplorerOptions options = new InternetExplorerOptions();
//    options.setCapability(InternetExplorerOptions.IE_OPTIONS, Map.of("ie.ignoreProcessIdMatchingIeMode", true));
    options.attachToEdgeChrome();
    options.withEdgeExecutablePath("C:\\Program Files (x86)\\Microsoft\\Edge\\Application\\msedge.exe");

//    options.attachToEdgeChrome();
//    options.withAttachTimeout(Duration.ofSeconds(10));
//    options.setPlatformName(Platform.WINDOWS.name());
//    options.requireWindowFocus();
//    options.destructivelyEnsureCleanSession();
//    options.ignoreZoomSettings();
//    options.introduceFlakinessByIgnoringSecurityDomains();
//    options.setCapability("nativeEvents", true);

    return options;
  }
}
