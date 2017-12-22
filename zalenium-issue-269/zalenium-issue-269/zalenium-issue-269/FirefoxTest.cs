using System;
using OpenQA.Selenium;
using OpenQA.Selenium.Remote;
using OpenQA.Selenium.Firefox;

namespace zaleniumissue269
{
    public class FirefoxTest
    {
        static void Main(string[] args)
        {

            FirefoxProfile firefoxHeadlessProfile = new FirefoxProfile
            {
                DeleteAfterUse = true
            };
            firefoxHeadlessProfile.SetPreference("browser.cache.disk.enable", false);
            firefoxHeadlessProfile.SetPreference("browser.cache.memory.enable", false);
            firefoxHeadlessProfile.SetPreference("browser.cache.offline.enable", false);
            firefoxHeadlessProfile.SetPreference("network.http.use-cache", false);

            FirefoxOptions firefoxHeadlessOptions = new FirefoxOptions
            {
                Profile = firefoxHeadlessProfile
            };
            firefoxHeadlessOptions.AddArgument("--headless");

            DesiredCapabilities desiredCapabilities = (DesiredCapabilities)firefoxHeadlessOptions.ToCapabilities();
            desiredCapabilities.SetCapability("zal:idleTimeout", 300);
            desiredCapabilities.SetCapability("zal:tz", "America/Chicago");
            desiredCapabilities.SetCapability("zal:recordVideo", false);


            Uri gridUrl = new Uri("http://localhost:4444/wd/hub");

            IWebDriver driver = new RemoteWebDriver(gridUrl, desiredCapabilities)
            {
                Url = "http://www.google.com"
            };
            driver.Quit();
        }
    }
}
