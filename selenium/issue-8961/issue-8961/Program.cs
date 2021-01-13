using System.Collections.Generic;
using System.Threading.Tasks;
using OpenQA.Selenium.Firefox;
using OpenQA.Selenium.Chrome;
using OpenQA.Selenium.Remote;
using System;

namespace issue_8961
{
    class Program
    {
        static void Main(string[] args)
        {
            List<Task> tasks = new List<Task>();
            for (int i = 1; i <= 20; i++)
            {
                Task t = Task.Run(() => DriverStuff());
                tasks.Add(t);
            }
            Task.WaitAll(tasks.ToArray());
        }

        public static void DriverStuff()
        {
            //FirefoxDriver driver = new FirefoxDriver();
            //ChromeDriver driver = new ChromeDriver();
            Uri grid = new Uri("http://localhost:4444");
            RemoteWebDriver driver = new RemoteWebDriver(grid, new FirefoxOptions());
            driver.Navigate().GoToUrl("https://www.bing.com");
            driver.Quit();
        }
    }
}
