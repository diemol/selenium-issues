from selenium import webdriver
from selenium.webdriver.common import proxy

# url='http://127.0.0.1:4444/wd/hub'
# firefox_options = webdriver.FirefoxOptions()
# firefox_options.headless = True
# firefox_options.proxy = proxy.Proxy({
#     "proxyType": proxy.ProxyType.MANUAL,
#     "httpProxy": "127.0.0.1:1080",
#     "socksProxy": "127.0.0.1:1080",
# })
# driver = webdriver.Remote(
#     command_executor=url,
#     options=firefox_options
# )
# driver.get("http://www.google.com")
# driver.quit() 

firefox_options = webdriver.FirefoxOptions()
firefox_options.headless = True
firefox_options.log.level = "trace"
firefox_options.proxy = proxy.Proxy({
    "proxyType": proxy.ProxyType.MANUAL,
    "httpProxy": "127.0.0.1:1080",
    "socksProxy": "127.0.0.1:1080",
})
driver = webdriver.Firefox(options=firefox_options)
driver.get("http://www.google.com")
driver.quit() 
