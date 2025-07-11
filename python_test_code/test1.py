from selenium import webdriver
from selenium.webdriver.common.by import By
from selenium.webdriver.common.keys import Keys
import time
# Enable logging in ChromeOptions
from selenium.webdriver.chrome.options import Options

# Initialize WebDriver
driver = webdriver.Chrome()  # or webdriver.Firefox()




# driver.get("https://blogapp-krlf.onrender.com/")

# try:
#     assert "Expected Title" in driver.title
# except AssertionError:
#     print("Title mismatch!")

# # Try interacting with a button
# try:
#     button = driver.find_element(By.ID, "submit")
#     button.click()
# except Exception as e:
#     print("Button problem:", e)

# driver = webdriver.Chrome()  # if chromedriver is installed globally


# --------------------------------------------------------------------------------------
# Step 1: Go to the login page
driver.get("https://kolkata.bugbash.live/signin")  # Replace with your login URL
time.sleep(2)  # Wait for page to load

# Step 2: Enter username and password
driver.find_element(By.ID, "username").send_keys("demouser")
driver.find_element(By.ID, "password").send_keys("testingisfun99")

# Step 3: Submit the login form
driver.find_element(By.XPATH, "//button[@id='login-btn']").click()

# Step 4: Wait and check login success (check page title or URL)
time.sleep(3)
if "/" in driver.current_url:
    print("✅ Login successful!")
else:
    print("❌ Login failed!")
# --------------------------------------------------------------------------------------



driver.get("https://apollo.aims2health.in/home")  # replace with your mystery website

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



# Step 5: Close the browser
driver.quit()


