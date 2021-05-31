import os
from selenium import webdriver

grid_url = "http://localhost:4444/wd/hub"
def get_driver():
    return webdriver.Remote(
        command_executor=grid_url,
        options=webdriver.FirefoxOptions())
try:    
    driver = get_driver()
    driver.get("http://127.0.0.1:8080")
except Exception as e:
        print(e)