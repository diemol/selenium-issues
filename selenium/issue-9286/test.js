const {Builder} = require('selenium-webdriver');
const fs = require('fs');

/*
  App used: https://github.com/Splode/pomotroid/releases
  ChromeDriver 83, as the app needs it to be automated
  https://chromedriver.storage.googleapis.com/index.html?path=83.0.4103.39/
 */
(async function example() {
  let driver = await new Builder()
    // The "9515" is the port opened by chrome driver.
    .usingServer('http://localhost:9515')
    .withCapabilities({
      'goog:chromeOptions': {
        // Here is the path to your Electron binary.
        binary: '/Users/diegomolina/Downloads/Pomotroid.app/Contents/MacOS/Pomotroid'
      }
    })
    .forBrowser('chrome')
    .build();
  try {
    console.log(await driver.getTitle());
    const encodedString = await driver.takeScreenshot();
    await fs.writeFileSync('./image.png', encodedString, 'base64');
  }
  finally{
    driver.quit();
  }
})();
