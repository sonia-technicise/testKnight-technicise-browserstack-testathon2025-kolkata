package tests;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import runners.BstackRunner;

import java.time.Duration;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class CheckoutTest extends BstackRunner {

    @Test
    void testCheckoutFlow() {
        try {
            System.out.println("\n[Test] Starting checkout flow...");
            driver.get("https://kolkata.bugbash.live/");
            WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(10));

            // ---- Sign In ----
            driver.findElement(By.id("Sign In")).click();
            System.out.println("-> Sign In page displayed.");
            Thread.sleep(2000);

            WebElement usernameDropdown = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//div[@id='username']//div[text()='Select Username']")));
            usernameDropdown.click();
            Thread.sleep(2000);
            wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//div[contains(@class, 'css-') and text()='demouser']"))).click();
            System.out.println("-> Username 'demouser' selected.");

            WebElement passwordDropdown = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//div[@id='password']//div[text()='Select Password']")));
            passwordDropdown.click();
            Thread.sleep(2000);
            wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//div[contains(@class, 'css-') and text()='testingisfun99']"))).click();
            System.out.println("-> Password 'testingisfun99' selected.");

            wait.until(ExpectedConditions.elementToBeClickable(By.id("login-btn"))).click();
            System.out.println("-> Login button clicked.");
            Thread.sleep(3000);

            // ---- Add Product to Cart ----
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
            Thread.sleep(3000);

            // ---- Open Cart and Validate Product ----
            System.out.println("-> Checking cart contents before checkout...");
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("float-cart--open")));
            List<WebElement> cartItems = driver.findElements(By.xpath("//div[contains(@class, 'float-cart__shelf-container')]//div[contains(@class, 'shelf-item')]"));

            boolean productInCart = false;
            for (WebElement item : cartItems) {
                WebElement title = item.findElement(By.className("title"));
                if (title.getText().trim().equalsIgnoreCase(productName)) {
                    productInCart = true;
                    System.out.println("Verified: '" + productName + "' is in the cart.");
                    break;
                }
            }

            assertTrue(productInCart, "ERROR: '" + productName + "' was not found in the cart!");

            // ---- Proceed to Checkout ----
            if (productInCart) {
                WebElement checkoutBtn = driver.findElement(By.className("buy-btn"));
                checkoutBtn.click();
                System.out.println("-> Successfully clicked on the checkout button.");
            }

            Thread.sleep(3000);

        } catch (Exception e) {
            throw new RuntimeException("Checkout flow failed: " + e.getMessage(), e);
        }
    }
}
