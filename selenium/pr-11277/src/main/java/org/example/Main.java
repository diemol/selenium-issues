package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.io.Zip;
import org.openqa.selenium.json.Json;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.http.HttpClient;
import org.openqa.selenium.remote.http.HttpMethod;
import org.openqa.selenium.remote.http.HttpRequest;
import org.openqa.selenium.remote.http.HttpResponse;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

import static org.openqa.selenium.remote.http.Contents.string;

public class Main {

  public static void main(String[] args) throws InterruptedException, IOException {
    FirefoxOptions options = new FirefoxOptions();
    options.addPreference("browser.download.manager.showWhenStarting", false);
    options.addPreference("browser.helperApps.neverAsk.saveToDisk", "images/jpeg, application/pdf, application/octet-stream");
    options.addPreference("pdfjs.disabled", true);

    URL gridUrl = new URL("http://localhost:4444");
    RemoteWebDriver driver =  new RemoteWebDriver(gridUrl, options);
    driver.get("http://the-internet.herokuapp.com/download");
    WebElement element = driver.findElement(By.cssSelector(".example a"));
    element.click();

    Thread.sleep(5 * 1000);

    HttpRequest request = new HttpRequest(
      HttpMethod.GET,
      String.format("/session/%s/se/file", driver.getSessionId()));
    request.addQueryParameter("filename", "my_appointments-1.pdf");
    try (HttpClient client = HttpClient.Factory.createDefault().createClient(gridUrl)) {
      HttpResponse response = client.execute(request);
      Map<String, Object> map = new Json().toType(string(response), Json.MAP_TYPE);
      String encodedContents = map.get("contents").toString();
//      Files.writeString(
//        Paths.get("/Users/diegomolina/Downloads/download.zip"),
//        encodedContents);
      Zip.unzip(encodedContents, new File("/Users/diegomolina/Downloads/selenium"));
    }

    driver.quit();
  }
}
