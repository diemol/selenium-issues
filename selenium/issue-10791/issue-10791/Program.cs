using OpenQA.Selenium;
using OpenQA.Selenium.IE;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading;
using System.Threading.Tasks;

namespace issue_10791
{
    class Program
    {
        static void Main(string[] args)
        {
            var ieOptions = new InternetExplorerOptions()
            {
                PageLoadStrategy = PageLoadStrategy.None,
                Proxy = null,
                AttachToEdgeChrome = true,
                EdgeExecutablePath = @"C:\Program Files (x86)\Microsoft\Edge\Application\msedge.exe"
            };

            var webDriver = new InternetExplorerDriver(ieOptions);
            webDriver.Navigate().GoToUrl("http://bing.com");
            var firstHandler = webDriver.CurrentWindowHandle;
            webDriver.SwitchTo().NewWindow(WindowType.Tab);
            webDriver.Navigate().GoToUrl("http://google.com");
            webDriver.SwitchTo().Window(firstHandler);
            Console.WriteLine(webDriver.WindowHandles.Count);
            webDriver.SwitchTo().Window(firstHandler);
            var newWindowHandle = webDriver.CurrentWindowHandle;
            Console.WriteLine("First handle: " + firstHandler);
            foreach (string window in webDriver.WindowHandles)
            {
                Console.WriteLine("Handle: " + window);
                if(newWindowHandle != window)
                {
                    webDriver.SwitchTo().Window(window);
                    break;
                }
            }
            Thread.Sleep(5000);

            //Automation on first page
            //var searchTextBox = webDriver.FindElement(By.Id("sb_form_q"));
            //var searchButton = webDriver.FindElement(By.Id("search_icon"));
            //searchTextBox.SendKeys("Test");
            //searchButton.Click();

            webDriver.Quit();
        }
    }
}
