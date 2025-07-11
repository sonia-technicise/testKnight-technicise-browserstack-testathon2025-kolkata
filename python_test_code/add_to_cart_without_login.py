from selenium import webdriver
from selenium.webdriver.common.by import By
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC
import time

driver = webdriver.Chrome()
driver.maximize_window()
wait = WebDriverWait(driver, 10)

driver.get("https://kolkata.bugbash.live")

try:
    wait.until(EC.presence_of_element_located((By.CLASS_NAME, "shelf-item__buy-btn")))
    cart_before = driver.find_element(By.CLASS_NAME, "bag__quantity").text
    print(f"Cart before click: {cart_before}")

    add_button = driver.find_element(By.CLASS_NAME, "shelf-item__buy-btn")
    add_button.click()
    print("Clicked 'Add to cart' without login")
    time.sleep(2)

    cart_after = driver.find_element(By.CLASS_NAME, "bag__quantity").text
    print(f"Cart after click: {cart_after}")

    if cart_after != cart_before:
        print("Item added to cart without login!")
    else:
        print("Cart did not update. Might require login.")

except Exception as e:
    print("Failed to interact with 'Add to cart' button:", str(e))


driver.quit()
