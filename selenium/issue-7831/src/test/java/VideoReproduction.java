import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class VideoReproduction {

    public static void main(String[] args){

        WebDriver webDriver = new ChromeDriver();
        try {
            webDriver.get("https://media.w3.org/2010/05/sintel/trailer.mp4");
            System.out.println(webDriver.getCurrentUrl());
            webDriver.get("http://www.google.com");
            System.out.println(webDriver.getCurrentUrl());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            System.out.println("Quitting");
            webDriver.quit();
        }

    }
}
