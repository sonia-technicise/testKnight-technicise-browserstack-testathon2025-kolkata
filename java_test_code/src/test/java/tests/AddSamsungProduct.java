package tests;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import runners.BstackRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AddSamsungProduct extends BstackRunner {

    @Test
    void testAddSamsungProductToCart() {
        System.out.println("\n Filtering and adding Samsung product to cart...");
        String productNameToFind = "Galaxy S20";
        boolean productFound = false;

        try {
            driver.get("https://kolkata.bugbash.live");

            // Click the Samsung filter
            System.out.println("Filtering by the 'Samsung' vendor type...");
            WebElement samsungFilter = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//span[text()='Samsung']")));
            samsungFilter.click();
            Thread.sleep(2000); // Give time for filter to apply
            System.out.println("-> Clicked on 'Samsung' filter.");

            // Find all product cards
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
                throw new RuntimeException("Product '" + productNameToFind + "' not found on the page.");
            }

            // Close the floating cart
            System.out.println("Attempting to close the floating cart...");
            WebElement closeBtn = wait.until(ExpectedConditions.elementToBeClickable(By.className("float-cart__close-btn")));
            closeBtn.click();
            System.out.println("-> Floating cart closed.");

        } catch (Exception e) {
            throw new RuntimeException("Test failed: " + e.getMessage(), e);
        }
    }
}
