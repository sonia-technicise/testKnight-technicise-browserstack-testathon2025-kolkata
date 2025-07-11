from selenium import webdriver
from selenium.webdriver.common.by import By
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC
from selenium.webdriver.common.action_chains import ActionChains
from selenium.common.exceptions import TimeoutException
import time

USERNAME = "souviksur73@gmail.com"
PASSWORD = "CHOTA@123bheem"
SEARCH_TERM = "wireless mouse"

driver = webdriver.Chrome()
wait = WebDriverWait(driver, 20)

try:
    # Step 1: Go to Amazon.in
    driver.get("https://www.amazon.in/")
    
    # Step 2: Click "Account & Lists"
    account = wait.until(EC.element_to_be_clickable((By.ID, "nav-link-accountList-nav-line-1")))
    ActionChains(driver).move_to_element(account).click().perform()
    
    # Step 3: Login
    wait.until(EC.url_contains("/ap/signin"))
    email = wait.until(EC.visibility_of_element_located((By.CSS_SELECTOR, "input#ap_email, input[name='email']")))
    email.send_keys(USERNAME)
    driver.find_element(By.ID, "continue").click()

    password = wait.until(EC.visibility_of_element_located((By.ID, "ap_password")))
    password.send_keys(PASSWORD)
    driver.find_element(By.ID, "signInSubmit").click()

    # Step 4: Confirm login
    greet = wait.until(EC.visibility_of_element_located((By.ID, "nav-link-accountList-nav-line-1")))
    print("✅ Login successful!")

    # Step 5: Search for product
    search_box = wait.until(EC.visibility_of_element_located((By.ID, "twotabsearchtextbox")))
    search_box.send_keys(SEARCH_TERM)
    driver.find_element(By.ID, "nav-search-submit-button").click()

    # Step 6: Click the first product
    product = wait.until(EC.element_to_be_clickable((By.CSS_SELECTOR, "div.s-main-slot a.a-link-normal.a-text-normal, div.s-main-slot a.a-link-normal.s-no-outline")))
    product.click()

    # Switch to the new tab
    driver.switch_to.window(driver.window_handles[-1])

    # Step 7: Add to cart
    add_to_cart_btn = wait.until(EC.element_to_be_clickable((By.ID, "add-to-cart-button")))
    add_to_cart_btn.click()
    print("🛒 Product added to cart!")

except TimeoutException as e:
    print("❌ Failed at some step:", e)
finally:
    time.sleep(5)  # Just to see the result before quitting
    driver.quit()
