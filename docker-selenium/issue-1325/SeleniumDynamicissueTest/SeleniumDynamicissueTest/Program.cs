using System;
using System.Threading;
using OpenQA.Selenium.Chrome;
using OpenQA.Selenium.Firefox;
using OpenQA.Selenium.Remote;

namespace SeleniumDynamicissueTest
{
    class Program
    {
        static void Main(string[] args)
        {
            var driverOptions = new FirefoxOptions();

            driverOptions.AddAdditionalOption("se:recordVideo", true);
            driverOptions.AddAdditionalOption("se:timeZone", "US/Pacific");
            driverOptions.AddAdditionalOption("se:screenResolution", "1920x1080");

            var driver = new RemoteWebDriver(new Uri("http://localhost:4444"), driverOptions);
            driver.Navigate().GoToUrl("http://www.google.com");
            Thread.Sleep(5000);
            driver.Quit();
 
        }
    }
}
