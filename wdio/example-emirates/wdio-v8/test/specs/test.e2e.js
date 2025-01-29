describe('Set Headers Using CDP with Sauce Labs', () => {
    it('should set custom headers for all requests', async () => {
        // Enable the Network domain
        await browser.cdp('Network', 'enable');        

        // Set custom headers
        await browser.cdp('Network', 'setExtraHTTPHeaders', {
            headers: {
                'x-test-header': 'true',
                Authorization: `Basic ${Buffer.from('admin:admin').toString('base64')}`,
            },
        });

        // Listen for request events
        browser.on('Network.requestWillBeSent', (params) => {
            console.log('Request URL:', params.request.url);
            console.log('Request Headers:', params.request.headers);
        });

        // Load basic auth url
        await browser.url('https://the-internet.herokuapp.com/basic_auth');
    });
});