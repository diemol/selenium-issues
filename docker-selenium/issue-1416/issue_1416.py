# docker run -p 4444:4444 -p 7900:7900 --shm-size="2g" selenium/standalone-firefox:4.0.0-20211102
# pip install selenium -> 4.0.0
import time
from selenium import webdriver

firefox_options = webdriver.FirefoxOptions()
firefox_options.set_preference('intl.accept_languages', 'nl-NL, nl')
driver = webdriver.Remote(
    command_executor='http://localhost:4444',
    options=firefox_options
)
driver.get("https://www.google.com")
time.sleep(5)
driver.quit()
