import { expect, browser, $ } from '@wdio/globals'

describe('Set Headers Using BiDi with Sauce Labs', () => {
    it('should set custom headers for all requests', async () => {
        const mock = await browser.mock('https://the-internet.herokuapp.com/**');
        mock.request({
            headers: {
                'x-test-header': 'true',
                Authorization: `Basic ${Buffer.from('admin:admin').toString('base64')}`,
            },
        })

        await browser.url('https://the-internet.herokuapp.com/');

        const requests = mock.calls;
        requests.forEach(request => {
            console.log(`Request URL: ${request.request.url}`);
            console.log('Headers: ', request.request.headers);
        });

        // Load basic auth url
        await browser.url('https://the-internet.herokuapp.com/basic_auth');

        const bodyText = await $('body').getText();
        expect(bodyText).toContain('Congratulations! You must have the proper credentials.');
    });
});