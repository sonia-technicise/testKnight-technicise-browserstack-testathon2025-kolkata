from selenium import webdriver
from selenium.webdriver.common.by import By
from selenium.webdriver.common.keys import Keys
import time
from selenium.webdriver.chrome.options import Options

driver = webdriver.Chrome()  

driver.get("https://kolkata.bugbash.live/")
driver.maximize_window()


print("Title:", driver.title)

links = driver.find_elements(By.TAG_NAME, "a")
for link in links:
    href = link.get_attribute("href")
    if href:
        print("Checking link:", href)


options = Options()
options.set_capability("goog:loggingPrefs", {"browser": "ALL"})
driver = webdriver.Chrome(options=options)

logs = driver.get_log("browser")
for entry in logs:
    print(entry)

driver.quit()
