package tests;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;
import runners.BstackRunner;

import java.time.Duration;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

public class AddGoogleProducts extends BstackRunner {

    @Test
    @Order(1)
    void testOpenWebsite() {
        try {
            System.out.println("[Test 1] Opening the website...");
            driver.get("https://kolkata.bugbash.live/");
            wait.until(ExpectedConditions.presenceOfElementLocated(By.className("filters")));
            assertTrue(driver.getTitle().contains("StackDemo"));
            System.out.println("-> Website's homepage loaded successfully.");
        } catch (Exception e) {
            throw new RuntimeException("Failed to open website: " + e.getMessage(), e);
        }
    }

    @Test
    @Order(2)
    void testFiltering() {

        System.out.println("\n[Test 2] Filtering by vendor...");
        try {
            driver.get("https://kolkata.bugbash.live");

            WebElement googleFilter = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//span[text()='Google']")));
            googleFilter.click();
            Thread.sleep(2000); // Give time for products to filter
            System.out.println("-> Clicked on 'Google' filter.");
        } catch (Exception e) {
            throw new RuntimeException("Could not click 'Google' filter. Error: " + e.getMessage(), e);
        }
    }

    @Test
    @Order(3)
    void testCartAddition() {
        System.out.println("\n[Test 3] Adding product to cart...");
        String productNameToFind = "Pixel 4";
        boolean productFound = false;

        try {
            driver.get("https://kolkata.bugbash.live");
            // Wait for all products to load
            wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.className("shelf-item")));
            List<WebElement> products = driver.findElements(By.className("shelf-item"));

            for (WebElement product : products) {
                WebElement name = product.findElement(By.className("shelf-item__title"));
                if (name.getText().equalsIgnoreCase(productNameToFind)) {
                    WebElement addToCartBtn = product.findElement(By.className("shelf-item__buy-btn"));
                    addToCartBtn.click();
                    System.out.println("-> Clicked 'Add to cart' for product: " + productNameToFind);
                    productFound = true;
                    break;
                }
            }

            if (!productFound) {
                throw new RuntimeException("Product '" + productNameToFind + "' not found after filtering.");
            }

            // Optional: close the cart if needed
            WebElement closeBtn = wait.until(ExpectedConditions.elementToBeClickable(By.className("float-cart__close-btn")));
            closeBtn.click();
            System.out.println("-> Floating cart closed.");

        } catch (Exception e) {
            throw new RuntimeException("Cart addition failed: " + e.getMessage(), e);
        }
    }

}
