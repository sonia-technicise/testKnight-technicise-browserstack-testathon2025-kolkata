package tests;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import runners.BstackRunner;

import java.util.List;

public class AddGoogleProducts extends BstackRunner {

    @Test
    public void testGoogleProductCartFlow() {
        System.out.println("[Test] Starting Google product cart flow...");

        try {
            // Step 1: Open site
            driver.get("https://kolkata.bugbash.live");
            System.out.println("-> Site opened.");
            Thread.sleep(2000);

            // Step 2: Apply 'Google' filter
            WebElement googleFilter = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//span[text()='Google']")));
            googleFilter.click();
            Thread.sleep(2000); // Wait for filter to apply
            System.out.println("-> Clicked on 'Google' filter.");

            // Step 3: Find "Pixel 4" and add to cart
            String productNameToFind = "Pixel 4";
            boolean productFound = false;

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
                System.out.println("-> ERROR: Product '" + productNameToFind + "' not found after filtering.");
                return;
            }
            Thread.sleep(4000);

            // Step 4: Close floating cart
            WebElement closeBtn = wait.until(ExpectedConditions.elementToBeClickable(By.className("float-cart__close-btn")));
            closeBtn.click();
            System.out.println("-> Floating cart closed.");

            System.out.println("[Test] Google product cart flow completed successfully.");

        } catch (Exception e) {
            System.out.println("-> ERROR during testGoogleProductCartFlow: " + e.getMessage());
        }
    }
}
