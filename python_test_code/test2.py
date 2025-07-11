from selenium import webdriver
from selenium.webdriver.common.by import By
from selenium.webdriver.common.keys import Keys
from selenium.webdriver.chrome.options import Options
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC
import time

def setup_driver():
    options = Options()
    options.set_capability("goog:loggingPrefs", {"browser": "ALL"})
    driver = webdriver.Chrome(options=options)
    driver.maximize_window()
    return driver

def accept_cookies(driver):
    try:
        driver.find_element(By.ID, "gdpr-banner-accept").click()
        time.sleep(1)
    except:
        pass

# def signup(driver):
#     driver.get("https://www.etsy.com")
#     accept_cookies(driver)

#     # Click "Sign in" button (it's a menu opener now)
#     driver.find_element(By.XPATH, "//span[text()='Sign in']/ancestor::button").click()
#     time.sleep(2)

#     # Click the "Register" link in the dropdown
#     driver.find_element(By.LINK_TEXT, "Register").click()
#     time.sleep(2)

#     # Fill the sign-up form
#     driver.find_element(By.NAME, "email").send_keys("atanu_test123@gmail.com")
#     driver.find_element(By.NAME, "first_name").send_keys("Atanu")
#     driver.find_element(By.NAME, "password").send_keys("Test@12345")
#     driver.find_element(By.NAME, "submit_attempt").click()

#     time.sleep(5)
#     if "etsy.com" in driver.current_url:
#         print("✅ Sign-up attempted!")
#     else:
#         print("❌ Sign-up failed!")


# def login(driver):
#     driver.get("https://www.etsy.com/signin")

#     try:
#         # Wait until email field is present
#         WebDriverWait(driver, 10).until(
#             EC.presence_of_element_located((By.NAME, "email"))
#         )

#         # Fill login form
#         driver.find_element(By.NAME, "email").send_keys("atanu@gmail.com")
#         driver.find_element(By.NAME, "password").send_keys("Atanu@@")

#         # Click sign-in button
#         driver.find_element(By.XPATH, "//button[@value='sign-in']").click()

#         # Wait for potential redirect or error
#         time.sleep(5)
#         print("✅ Login attempted!")
#     except Exception as e:
#         print("❌ Login failed:", str(e))

# def check_navigation(driver):
#     driver.get("https://www.etsy.com")
#     print("Title:", driver.title)

#     links = driver.find_elements(By.TAG_NAME, "a")
#     for link in links[:10]:  # check first 10 links to limit output
#         href = link.get_attribute("href")
#         if href:
#             print("Checking link:", href)

def search_product(driver):
    driver.get("https://www.etsy.com")
    accept_cookies(driver)

    search_input = driver.find_element(By.NAME, "search_query")
    search_input.send_keys("mug")
    search_input.send_keys(Keys.RETURN)
    time.sleep(3)

    if "mug" in driver.title.lower():
        print("✅ Search functionality working.")
    else:
        print("❌ Search did not work as expected.")

def print_console_logs(driver):
    logs = driver.get_log("browser")
    for entry in logs:
        print(entry)

# Run All Tests
driver = setup_driver()
# signup(driver)
# login(driver)
# check_navigation(driver)
search_product(driver)
print_console_logs(driver)
driver.quit()
