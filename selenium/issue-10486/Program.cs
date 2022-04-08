using System;
using OpenQA.Selenium.Chrome;
using OpenQA.Selenium.DevTools;
using OpenQA.Selenium.DevTools.V96.Page;
using DevToolsSessionDomains = OpenQA.Selenium.DevTools.V96.DevToolsSessionDomains;

namespace issue_10486
{
    class Program
    {
        static void Main(string[] args)
        {
            var driver = new ChromeDriver();
            IDevTools devTool = driver;
            var session = devTool.GetDevToolsSession();
            var domains = session.GetVersionSpecificDomains<DevToolsSessionDomains>();
            domains.Page.Enable(new EnableCommandSettings());
            domains.Page.AddScriptToEvaluateOnNewDocument(new AddScriptToEvaluateOnNewDocumentCommandSettings()
            {
                Source = "Object.defineProperty(navigator, 'webdriver', { get: () => undefined });" +
                         @"Object.defineProperty(navigator, 'platform', { get: () => 'MacOS'}); " +
                         ""
            });
            driver.Navigate().GoToUrl("https://selenium.dev");
            driver.Quit();
        }
    }
}