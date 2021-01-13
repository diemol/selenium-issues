using System;
using System.Threading.Tasks;
using OpenQA.Selenium.Firefox;

namespace issue_8961
{
    class Program
    {
        static void Main(string[] args)
        {
            for (int i = 1; i <= 1; i++)
            {
                Task.Run(() => DriverStuff());
            }
            Console.ReadLine();
        }

        public static void DriverStuff()
        {
            FirefoxDriver driver = new FirefoxDriver();
            driver.Navigate().GoToUrl("https://www.bing.com");
            driver.Quit();
        }
    }
}