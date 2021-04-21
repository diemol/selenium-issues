// run with ts-node test.ts
import { remote } from "webdriverio";

const opt = {
  hostname: 'localhost',
  port: 4444,
  path: '/wd/hub',
  capabilities: {
    browserName: 'chrome',
  }
};

void (async () => {
  const driver = await remote(opt);
  await driver.url('https://webdriver.io')
  await driver.deleteSession()
})();