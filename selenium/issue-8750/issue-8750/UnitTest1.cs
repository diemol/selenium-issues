using System.Threading;
using NUnit.Framework;
using OpenQA.Selenium;
using OpenQA.Selenium.Chrome;
using OpenQA.Selenium.DevTools;
using OpenQA.Selenium.DevTools.Network;

namespace issue_8750
{
    public class Tests
    {
        private IWebDriver driver;

        [SetUp]
        public void Setup()
        {
            driver = new ChromeDriver();
        }

        [Test]
        public void Test1()
        {
            IDevTools devTools = driver as IDevTools;
            DevToolsSession session = devTools.CreateDevToolsSession();
            SetBlockedURLsCommandSettings blockedUrlSettings = new SetBlockedURLsCommandSettings();
            blockedUrlSettings.Urls = new string[]
            {
                "http://automationpractice.com/img/p/1/6/16-large_default.jpg",
                "http://automationpractice.com/modules/blockbanner/img/sale70.png",
            };
            session.Network.Enable(new EnableCommandSettings());
            session.Network.SetBlockedURLs(blockedUrlSettings);
            driver.Manage().Window.Maximize();
            driver.Navigate().GoToUrl("http://automationpractice.com/");
            Thread.Sleep(10000);
        }

        [TearDown]
        public void TearDown()
        {
            driver.Quit();
        }
    }
}