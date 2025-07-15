package tests;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import runners.BstackRunner;

import java.time.Duration;
import java.util.List;

@TestMethodOrder(OrderAnnotation.class)
public class ProductRemovalTest extends BstackRunner {

    @Test
    @Order(1)
    public void testProductRemoval() throws InterruptedException {

        System.out.println("Automation testing starts");
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        System.out.println("The web browser is opened successfully.");

        driver.get("https://kolkata.bugbash.live/");
        Thread.sleep(2000);
        System.out.println("The website's homepage loads successfully.");

        // Apply Samsung filter
        System.out.println("Filtering by the 'Samsung' vendor type");
        try {
            WebElement samsungFilter = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//input[@value='Samsung']/following-sibling::span[@class='checkmark' and text()='Samsung']")));
            samsungFilter.click();
            Thread.sleep(2000);
            System.out.println("Clicked on 'Samsung' filter.");
        } catch (Exception e) {
            System.out.println("Could not click 'Samsung' filter. Error: " + e.getMessage());
        }

        // Add "Galaxy S20" to cart
        addProductToCart("Galaxy S20");

        // Add "Galaxy Note 20" to cart
        addProductToCart("Galaxy Note 20");

        Thread.sleep(2000);

        // Remove "Galaxy S20" from cart
        removeProductFromCart("Galaxy S20");

        // Close the cart
        System.out.println("Attempting to close the floating cart...");
        try {
            WebElement closeBtn = wait.until(ExpectedConditions.elementToBeClickable(By.className("float-cart__close-btn")));
            closeBtn.click();
            System.out.println("Clicked the exit button of the floating cart.");
            Thread.sleep(2000);
        } catch (Exception e) {
            System.out.println("Could not click the floating cart exit button. Error: " + e.getMessage());
        }
    }

    public void addProductToCart(String productName) throws InterruptedException {
        List<WebElement> products = driver.findElements(By.className("shelf-item"));
        boolean found = false;
        for (WebElement product : products) {
            if (product.getText().contains(productName)) {
                try {
                    WebElement addToCartBtn = product.findElement(By.className("shelf-item__buy-btn"));
                    addToCartBtn.click();
                    System.out.println("Clicked 'Add to Cart' for product: " + productName);
                    found = true;
                    break;
                } catch (Exception e) {
                    System.out.println("Found product '" + productName + "' but couldn't click the button. Error: " + e.getMessage());
                    break;
                }
            }
        }
        if (!found) {
            System.out.println("Product '" + productName + "' not found on the page.");
        }
        Thread.sleep(2000);
    }

    public void removeProductFromCart(String productName) throws InterruptedException {
        System.out.println("Attempting to remove cart item: " + productName);
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("float-cart--open")));
            System.out.println("Floating cart is visible.");

            List<WebElement> cartItems = driver.findElements(
                By.xpath("//div[contains(@class, 'float-cart__shelf-container')]//div[contains(@class, 'shelf-item')]"));

            boolean removed = false;
            if (cartItems.isEmpty()) {
                System.out.println("No items found in the cart to remove.");
            } else {
                for (WebElement item : cartItems) {
                    if (item.getText().contains(productName)) {
                        try {
                            WebElement removeBtn = item.findElement(By.className("shelf-item__del"));
                            removeBtn.click();
                            System.out.println("Successfully removed '" + productName + "' from the cart.");
                            removed = true;
                            break;
                        } catch (Exception e) {
                            System.out.println("Found '" + productName + "' but couldn't click its remove button. Error: " + e.getMessage());
                            break;
                        }
                    }
                }
                if (!removed) {
                    System.out.println("Product '" + productName + "' was not found in the cart.");
                }
            }
        } catch (Exception e) {
            System.out.println("Failed during cart item removal process. Error: " + e.getMessage());
        }
        Thread.sleep(2000);
    }
}
