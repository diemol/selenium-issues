from time import sleep

from selenium import webdriver
from selenium.webdriver.common.by import By


driver = webdriver.Firefox()
driver.get("https://wujie-micro.github.io/demo-main-react/?vue3=%2Fdemo-vue3%2Fhome#/vue3")
sleep(10)
title = driver.title
print(title)
shadow_host = driver.find_element(By.TAG_NAME, 'wujie-app')
shadow_root = shadow_host.shadow_root
btn = shadow_root.find_element(By.CSS_SELECTOR, '.el-button')
print('btn', btn)
btn.click()
sleep(10)
driver.quit()
