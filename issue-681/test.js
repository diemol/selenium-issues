'use strict';

const assert = require('assert');
const {Builder, By, Key, promise, until} = require('selenium-webdriver');

promise.USE_PROMISE_MANAGER = false;

describe('Google Search', function() {
    let driver;

    beforeEach(async function() {
        driver = await new Builder().forBrowser('firefox').build();
    });

    afterEach(async function() {
        await driver.quit();
    });

    it('example', async function() {
        await driver.get('https://www.google.com/ncr');

        await driver.findElement(By.name('q')).sendKeys('webdriver', Key.RETURN);

        await driver.wait(until.titleIs('webdriver - Google Search'), 1000);

        let url = await driver.getCurrentUrl();
        assert.ok(
            url.startsWith('https://www.google.com/search'),
            'unexpected url: ' + url);
    });
});
