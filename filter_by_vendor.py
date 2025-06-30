from selenium import webdriver
from selenium.webdriver.common.by import By
import time

driver = webdriver.Chrome()
driver.get("https://kolkata.bugbash.live")
time.sleep(3)

vendors = ["Apple", "Samsung", "Google", "OnePlus"]

for vendor in vendors:
    try:
        print(f"\n Testing vendor filter: {vendor}")

        vendor_button = driver.find_element(By.XPATH, f"//button[text()='{vendor}']")
        vendor_button.click()
        time.sleep(3)

        product_titles = driver.find_elements(By.CLASS_NAME, "MuiCardContent-root")

        if not product_titles:
            print("No products found after filter!")
            continue

        for product in product_titles:
            title = product.text.lower()
            if vendor.lower() not in title:
                print(f"Incorrect product found under {vendor}: {title}")
            else:
                print(f"{vendor} product: {title.splitlines()[0]}")

    except Exception as e:
        print(f"Error testing {vendor}: {e}")

driver.quit()
