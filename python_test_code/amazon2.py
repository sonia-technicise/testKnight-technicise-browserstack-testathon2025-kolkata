from selenium import webdriver
from selenium.webdriver.common.by import By
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC
from selenium.webdriver.common.action_chains import ActionChains

USERNAME = "souviksur73@gmail.com"
PASSWORD = "CHOTA@123bheem"
SEARCH_TERM = "wireless mouse"

driver = webdriver.Chrome()
wait = WebDriverWait(driver, 20)

# Launch browser
driver = webdriver.Chrome()
# driver.get("https://www.amazon.in/s?k=mobile")
# wait = WebDriverWait(driver, 10)

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

    #Step 5: Search for a product
search_box = wait.until(EC.visibility_of_element_located((By.ID, "twotabsearchtextbox")))
search_box.send_keys(SEARCH_TERM)
driver.find_element(By.ID, "nav-search-submit-button").click()

# Step 1: Wait for list items with both attributes
wait.until(EC.presence_of_all_elements_located((By.CSS_SELECTOR, 'div[role="listitem"][data-cel-widget]')))
product_cards = driver.find_elements(By.CSS_SELECTOR, 'div[role="listitem"][data-cel-widget]')

product_link = None

# Step 2: Find first valid product link and open it
for product in product_cards:
    try:
        # Find the h2 -> a element which has the product link
        link_element = product.find_element(By.CSS_SELECTOR, "h2 a")
        href = link_element.get_attribute("href")
        if href:
            product_link = href
            print("✅ Found product link:", product_link)
            break
    except:
        continue

# Step 3: Open product page and add to cart
if product_link:
    driver.get(product_link)
    print("🔗 Opening product page...")

    # Step 4: Wait for and click "Add to Cart"
    add_to_cart_button = wait.until(EC.element_to_be_clickable((By.ID, "add-to-cart-button")))
    add_to_cart_button.click()
    print("🛒 Product added to cart!")
else:
    raise Exception("❌ No valid product found.")
