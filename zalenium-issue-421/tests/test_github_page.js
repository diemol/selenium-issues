var expect = require('chai').expect;

describe('GitHub.com page', function() {
    this.timeout(60000);
    it('should have the right title for GitHub.com - the fancy generator way', function () {
        browser.url('https://github.com/');
        var title = browser.getTitle();
        expect(title).to.equal("The world's leading software development platform · GitHub");
    });

});

