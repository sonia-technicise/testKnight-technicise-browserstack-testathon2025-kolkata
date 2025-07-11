from selenium import webdriver
from selenium.webdriver.common.by import By
from selenium.webdriver.common.keys import Keys
import time
# Enable logging in ChromeOptions
from selenium.webdriver.chrome.options import Options

# Initialize WebDriver
driver = webdriver.Chrome()  # or webdriver.Firefox()

# --------------------------------------------------------------------------------------
driver.get("https://www.etsy.com")
driver.maximize_window()

# Step 2: Accept cookies if the prompt shows
try:
    driver.find_element(By.ID, "gdpr-banner-accept").click()
    time.sleep(1)
except:
    pass

# Step 3: Click on "Sign in" at the top
driver.find_element(By.XPATH, "//button[contains(text(), 'Sign in')]").click()
time.sleep(2)

# Step 4: Click on "Register" link
driver.find_element(By.XPATH, "//button[contains(text(), 'Register')]").click()
time.sleep(2)

# Step 5: Fill the sign-up form
driver.find_element(By.NAME, "email").send_keys("atanu@gmail.com")
driver.find_element(By.NAME, "first_name").send_keys("atanu")
driver.find_element(By.NAME, "password").send_keys("12345")

# Step 6: Click "Register" button to submit the form
driver.find_element(By.NAME, "submit_attempt").click()

# Step 7: Wait and check result
time.sleep(5)
if "etsy.com" in driver.current_url:
    print("✅ Sign-up attempted!")
else:
    print("❌ Sign-up failed!")
# --------------------------------------------------------------------------------------


# --------------------------------------------------------------------------------------
# Step 1: Go to the login page
driver.get("https://www.etsy.com/login")  # Replace with your login URL
time.sleep(2)  # Wait for page to load

# Step 2: Enter username and password
driver.find_element(By.NAME, "username").send_keys("sangeeta")
driver.find_element(By.NAME, "password").send_keys("Mycur@tio2019")

# Step 3: Submit the login form
driver.find_element(By.XPATH, "//button[@type='submit']").click()

# Step 4: Wait and check login success (check page title or URL)
time.sleep(3)
if "/" in driver.current_url:
    print("✅ Login successful!")
else:
    print("❌ Login failed!")
# --------------------------------------------------------------------------------------



# --------------------------------------------------------------------------------------
# Home route to check all the navigation tab

driver.get("https://www.etsy.com/home")  # replace with your mystery website

print("Title:", driver.title)

links = driver.find_elements(By.TAG_NAME, "a")
for link in links:
    href = link.get_attribute("href")
    if href:
        print("Checking link:", href)


options = Options()
options.set_capability("goog:loggingPrefs", {"browser": "ALL"})
driver = webdriver.Chrome(options=options)

# Later in code
logs = driver.get_log("browser")
for entry in logs:
    print(entry)
# ------------------------------------------------------------------------------------------



# ------------------------------------------------------------------------------------------
# Go to Etsy homepage
driver.get("https://www.etsy.com")

# Accept cookies if prompted
try:
    driver.find_element(By.ID, "gdpr-banner-accept").click()
    time.sleep(1)
except:
    pass  # cookie banner might not always appear

# Search for a product
search_input = driver.find_element(By.NAME, "search_query")
search_input.send_keys("mug")
search_input.send_keys(Keys.RETURN)

# Wait for results to load
time.sleep(3)

# Check if search results appeared
if "mug" in driver.title.lower():
    print("✅ Search functionality working.")
else:
    print("❌ Search did not work as expected.")

# --------------------------------------------------------------------
# Close the browser
driver.quit()


