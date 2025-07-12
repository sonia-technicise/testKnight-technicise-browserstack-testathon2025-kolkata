package tests;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import runners.BstackRunner;

import java.util.List;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AddMultipleProductsTest extends BstackRunner {

    String[] productNames = {
        "iPhone 12", "iPhone 12 Mini", "iPhone 12 Pro Max", "iPhone 12 Pro",
        "iPhone 11", "iPhone 11 Pro", "iPhone XS", "iPhone XR", "iPhone XS Max",
        "Galaxy S20", "Galaxy S20+", "Galaxy S20 Ultra", "Galaxy S10", "Galaxy S9",
        "Galaxy Note 20", "Galaxy Note 20 Ultra",
        "Pixel 4", "Pixel 3", "Pixel 2",
        "One Plus 8", "One Plus 8T", "One Plus 8 Pro", "One Plus 7T", "One Plus 7", "One Plus 6T"
    };

    @Test
    @Order(1)
    void testAddMultipleProducts() {
        try {
            System.out.println("[Test] Starting automation testing for multiple products...");
            driver.get("https://kolkata.bugbash.live/");
            wait.until(ExpectedConditions.presenceOfElementLocated(By.className("filters")));
            System.out.println("-> Website homepage loaded.");

            for (String productName : productNames) {
                System.out.println("Testing product: " + productName);
                boolean productFound = false;

                wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.className("shelf-item")));
                List<WebElement> products = driver.findElements(By.className("shelf-item"));

                for (WebElement product : products) {
                    WebElement titleElement = product.findElement(By.className("shelf-item__title"));
                    if (titleElement.getText().equalsIgnoreCase(productName)) {
                        try {
                            WebElement addToCartBtn = product.findElement(By.className("shelf-item__buy-btn"));
                            wait.until(ExpectedConditions.elementToBeClickable(addToCartBtn));
                            addToCartBtn.click();
                            System.out.println("-> Clicked 'Add to Cart' for product: " + productName);
                            productFound = true;
                            break;
                        } catch (Exception e) {
                            System.out.println("Found product '" + productName + "' but couldn't click the button. Error: " + e.getMessage());
                            break;
                        }
                    }
                }

                if (!productFound) {
                    System.out.println("Product '" + productName + "' not found on the page.");
                }

                // Close floating cart
                System.out.println("Attempting to close the floating cart...");
                try {
                    WebElement closeBtn = wait.until(ExpectedConditions.elementToBeClickable(By.className("float-cart__close-btn")));
                    closeBtn.click();
                    System.out.println("-> Floating cart closed.");
                } catch (Exception e) {
                    System.out.println("Could not close the floating cart. Error: " + e.getMessage());
                }
            }

        } catch (Exception e) {
            throw new RuntimeException("Test failed: " + e.getMessage(), e);
        }
    }
}
