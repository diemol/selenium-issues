using System;
using System.Threading;
using NUnit.Framework;
using OpenQA.Selenium;
using OpenQA.Selenium.Chrome;
using OpenQA.Selenium.Support.UI;
using SeleniumExtras.WaitHelpers;

namespace Issue7834
{
    public class Issue7834Troubleshooting
    {
        [Test]
        public void Issue7834Test()
        {
            IWebDriver webDriver = new ChromeDriver();
            try
            {
                webDriver.Navigate().GoToUrl("https://login.microsoftonline.com/");
                var wait = new WebDriverWait(webDriver, TimeSpan.FromSeconds(10));
                var byEmail = By.XPath("//input[@type='email']");
                wait.Until(ExpectedConditions.ElementIsVisible(byEmail));
                webDriver.FindElement(byEmail).SendKeys("joe@gmail.com");
                webDriver.FindElement(By.XPath("//input[@value='Next']")).Click();
                Thread.Sleep(20000);
                //Switch to the alert
                var alertHandler = webDriver.SwitchTo().Alert(); 
                alertHandler.SetAuthenticationCredentials("joe@gmail.com", "a_fake_password"); 
                webDriver.SwitchTo().DefaultContent();
            }
            catch (Exception e)
            {
                Console.WriteLine(e);
                throw;
            }
            finally
            {
                webDriver.Quit();
            }
        }
        
    }
}