const webdriver = require('selenium-webdriver');
const BROWSER_NAME = webdriver.Browser.CHROME;
const chrome = require('selenium-webdriver/chrome');

async function getDriver() {
  const options = new chrome.Options();

  return new webdriver.Builder()
    .forBrowser(BROWSER_NAME)
    .withCapabilities(webdriver.Capabilities.chrome())
    .setChromeOptions(options).build();
}

async function doStuff(){

  // const firstDriver = await getDriver();
  // await firstDriver.get('https://google.com')
  //
  // const secondDriver = await getDriver();
  //
  // await firstDriver.quit();
  //
  // await secondDriver.get('https://youtube.com')
  // await secondDriver.get('https://reddit.com'); // <-- ECONNREFUSED here
  // await secondDriver.quit();
  //
  // await new Promise(r => setTimeout(r, 20000));

  const service = new chrome.ServiceBuilder()
    .loggingTo('/tmp/chrome_driver_logs.txt')
    .build();

  await chrome.setDefaultService(service)

  const options = new chrome.Options()

  // const driver = chrome.Driver.createSession(options, service)

  const firstDriver = new webdriver.Builder()
    .forBrowser('chrome')
    .build()

  await firstDriver.get('https://google.com')

  const secondDriver = new webdriver.Builder()
    .forBrowser('chrome')
    .build()

  await secondDriver.get('https://youtube.com')
  await firstDriver.quit()
  await secondDriver.get('https://reddit.com'); // <-- ECONNREFUSED here
  await secondDriver.quit()

  // await new Promise(r => setTimeout(r, 10000));
}

doStuff();
