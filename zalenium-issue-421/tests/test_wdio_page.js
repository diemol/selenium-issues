var expect = require('chai').expect;

describe('webdriver.io page', function() {
    this.timeout(60000);
    it('should have the right title for WebDriverIO - the fancy generator way', function () {
        browser.url('http://webdriver.io');
        var title = browser.getTitle();
        expect(title).to.equal('WebdriverIO - WebDriver bindings for Node.js');
    });

});

