var expect = require('chai').expect;

describe('Google.com page', function() {
    this.timeout(60000);
    it('should have the right title for Google.com - the fancy generator way', function () {
        browser.url('https://google.com/');
        var title = browser.getTitle();
        expect(title).to.equal("Google");
    });

});

