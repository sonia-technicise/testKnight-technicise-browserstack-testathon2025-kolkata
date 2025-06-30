from selenium import webdriver
from selenium.webdriver.common.by import By
from selenium.webdriver.common.keys import Keys
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC
import time

driver = webdriver.Chrome()
wait = WebDriverWait(driver, 10)
driver.get("https://kolkata.bugbash.live/signin")

wait.until(EC.element_to_be_clickable((By.ID, "username"))).click()
time.sleep(3)

#driver.find_element(By.XPATH, "//div[contains(@class,'css-yt9ioa-option')]").click()
driver.find_element(By.XPATH, "//div[contains(@class,'css-26l3qy-menu')]").click()

wait.until(EC.element_to_be_clickable((By.ID, "password"))).click()
time.sleep(3)
driver.find_element(By.XPATH, "//div[contains(@class,'css-26l3qy-menu')]").click()

driver.find_element(By.ID, "login-btn").click()
time.sleep(3)

if "Logout" in driver.page_source or "demouser" in driver.page_source:
    print("Login successful!")
else:
    print("Login failed!")

# driver.quit()
