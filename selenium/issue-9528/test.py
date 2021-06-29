from selenium import webdriver
from selenium.webdriver.firefox.firefox_binary import FirefoxBinary

grid_url = "http://localhost:4443"


def get_driver():
    firefox_options = webdriver.FirefoxOptions()
    firefox_options._binary = FirefoxBinary('/Volumes/Sauce/Firefox/Firefox 89.app/Contents/MacOS/firefox-bin')
    return webdriver.Remote(
        command_executor=grid_url,
        options=firefox_options)


try:
    driver = get_driver()
    for x in range(2000):
        print(x)
        driver.get("http://www.google.com")
        driver.current_window_handle
        driver.current_url
        driver.title
        # driver.manage().timeouts().getScriptTimeout();
    driver.quit()
except Exception as e:
    print(e)
