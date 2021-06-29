from selenium import webdriver

grid_url = "http://localhost:4444"


def get_driver():
    return webdriver.Remote(
        command_executor=grid_url,
        options=webdriver.FirefoxOptions())


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
