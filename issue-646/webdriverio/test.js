var webdriverio = require('webdriverio');
var options = {
    desiredCapabilities: {
        browserName: 'chrome'
    }
};

webdriverio
    .remote(options)
    .init()
    .url('http://www.google.com')
    .saveScreenshot('screen.png')
    .end()
    .catch(function(err) {
        console.log(err);
    });
