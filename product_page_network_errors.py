from selenium import webdriver
from selenium.webdriver.common.by import By
from selenium.webdriver.chrome.options import Options
import time

options = Options()
options.set_capability("goog:loggingPrefs", {"browser": "ALL"})

driver = webdriver.Chrome(options=options)
driver.get("https://kolkata.bugbash.live")

time.sleep(5)

logs = driver.get_log("browser")
for entry in logs:
    if "404" in entry["message"] or "failed-request" in entry["message"]:
        print(f"Found failed request: {entry['message']}")

driver.quit()
