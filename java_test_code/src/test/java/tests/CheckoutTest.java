package tests;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import runners.BstackRunner;

import java.time.Duration;
import java.util.List;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CheckoutTest extends BstackRunner {

    @Test
    @Order(1)
    void testOpenWebsite() {
        try {
            System.out.println("[Test 1] Opening the website...");
            driver.get("https://kolkata.bugbash.live/");
            wait.until(ExpectedConditions.presenceOfElementLocated(By.className("filters")));
            System.out.println("-> Website's homepage loaded successfully.");
        } catch (Exception e) {
            throw new RuntimeException("Failed to open website: " + e.getMessage(), e);
        }
    }

    @Test
    @Order(2)
    void testCheckoutFlow() {
        try {
            System.out.println("\n[Test 2] Starting checkout flow...");
            driver.get("https://kolkata.bugbash.live/");
            WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(10));

            // Sign In
            driver.findElement(By.id("Sign In")).click();
            Thread.sleep(2000);
            System.out.println("-> Sign In page displayed.");

            WebElement usernameDropdown = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@id='username']//div[text()='Select Username']")));
            usernameDropdown.click();
            Thread.sleep(1000);
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(@class, 'css-') and text()='demouser']"))).click();
            System.out.println("-> Username 'demouser' selected.");

            WebElement passwordDropdown = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@id='password']//div[text()='Select Password']")));
            passwordDropdown.click();
            Thread.sleep(1000);
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(@class, 'css-') and text()='testingisfun99']"))).click();
            System.out.println("-> Password 'testingisfun99' selected.");

            wait.until(ExpectedConditions.elementToBeClickable(By.id("login-btn"))).click();
            System.out.println("-> Login button clicked.");
            Thread.sleep(2000);

            // Add product to cart
            String productName = "Galaxy S20";
            wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.className("shelf-item")));
            List<WebElement> products = driver.findElements(By.className("shelf-item"));

            boolean productFound = false;
            for (WebElement product : products) {
                if (product.getText().contains(productName)) {
                    WebElement addToCartBtn = product.findElement(By.className("shelf-item__buy-btn"));
                    addToCartBtn.click();
                    System.out.println("-> Clicked 'Add to Cart' for: " + productName);
                    productFound = true;
                    break;
                }
            }

            if (!productFound) {
                throw new RuntimeException("Product '" + productName + "' not found.");
            }

            // Checkout flow
            System.out.println("-> Attempting to checkout the items in the cart...");
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("float-cart--open")));
            List<WebElement> cartItems = driver.findElements(By.xpath("//div[contains(@class, 'float-cart__shelf-container')]//div[contains(@class, 'shelf-item')]"));

            if (!cartItems.isEmpty()) {
                try {
                    WebElement checkoutBtn = driver.findElement(By.className("buy-btn"));
                    checkoutBtn.click();
                    System.out.println("-> Successfully clicked on the checkout button.");
                } catch (Exception e) {
                    throw new RuntimeException("Checkout button found but couldn't be clicked: " + e.getMessage(), e);
                }
            } else {
                System.out.println("-> No items found in the cart to checkout.");
            }

            Thread.sleep(2000);

        } catch (Exception e) {
            throw new RuntimeException("Checkout flow failed: " + e.getMessage(), e);
        }
    }
}
