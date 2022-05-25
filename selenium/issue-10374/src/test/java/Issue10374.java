import org.openqa.selenium.ImmutableCapabilities;
import org.openqa.selenium.MutableCapabilities;

import java.util.HashMap;

public class Issue10374 {

  public static void main (String[] args) {
    new ImmutableCapabilities("example", "value");
    new ImmutableCapabilities("example.key", "value");
    HashMap<String, Object> capsInMap = new HashMap<>();
    capsInMap.put("key", "value");
    new ImmutableCapabilities(capsInMap);

    MutableCapabilities mutableCapabilities = new MutableCapabilities(capsInMap);
    mutableCapabilities.setCapability("keyInMutable", "value");
    mutableCapabilities.setCapability("platformName", "value");
    mutableCapabilities.setCapability("se:video", "value");
    mutableCapabilities.setCapability("safari.video", "value");


  }

}
