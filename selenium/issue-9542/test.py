import pytest
import time
from selenium import webdriver
from selenium.common.exceptions import TimeoutException
from selenium.webdriver.common.by import By
from selenium.webdriver.common.keys import Keys
from selenium.webdriver.support.wait import WebDriverWait
from selenium.webdriver.support import  expected_conditions as ec


driver = None
def setup():
    global driver
    chrome_driver_path = '/usr/local/bin/chromedriver'
    gecko_driver_path = '/usr/local/bin/geckodriver'
    url = 'http://practice.automationtesting.in/my-account/'

    browser = 'chrome'
    if browser == 'chrome':
        driver = webdriver.Chrome(executable_path=chrome_driver_path)
    elif browser == 'firefox':
        driver = webdriver.Firefox(executable_path=gecko_driver_path)
    else:
        raise ValueError('Invalid Browser')

    driver.maximize_window()
    driver.set_page_load_timeout(10)
    # Do not mix implicit and explicit waits
    # driver.implicitly_wait(5)
    try:
        driver.get(url)
    except TimeoutException:
        print('Page not load')
        driver.quit()


def test_register_user():
    test_data = {
        'username' : 'user_mail_test@gmail.com',
        'password' : 'StR0nGp4AsSw0Rd'
    }
    # Page is full of css and js, find what you want to wait for to be present
    # before inreacting with the script, so validations are shown as expected.
    # As a workaround to show the issue is in the website, not in Selenium, and old fashioned
    # sleep to let all that JS load before interacting with the page.
    time.sleep(20)
    driver.find_element_by_id('reg_email').send_keys(test_data['username'])
    driver.find_element_by_id('reg_password').send_keys(test_data['password'])
    wait_driver = WebDriverWait(driver, 5, 0.2)
    try:
        button = wait_driver.until(ec.element_to_be_clickable((By.NAME,'register')))
        button.click()
    except TimeoutException:
        pytest.fail('Register button is not clickable')


def teardown():
    driver.quit()
