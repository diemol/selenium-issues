import { expect, browser, $ } from '@wdio/globals'

describe('Basic Auth with Sauce Labs', () => {
    it('navigates through site without basic auth challenges', async () => {
        await browser.url('https://the-internet.herokuapp.com/');

        // Load basic auth url
        await browser.url('https://the-internet.herokuapp.com/basic_auth');

        const bodyText = await $('body').getText();
        expect(bodyText).toContain('Congratulations! You must have the proper credentials.');
    });
});