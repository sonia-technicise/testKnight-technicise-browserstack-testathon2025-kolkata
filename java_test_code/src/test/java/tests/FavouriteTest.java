package tests;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import runners.BstackRunner;

import java.util.List;

public class FavouriteTest extends BstackRunner {

    @Test
    public void testFavouriteFlow() {
        try {
            System.out.println("Automation testing starts");

            // Step 1: Open homepage
            driver.get("https://kolkata.bugbash.live/signin/");
            System.out.println("The Signin page loads successfully.");
            Thread.sleep(2000);

            // Step 2: Login
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@id='username']//div[text()='Select Username']"))).click();
            System.out.println("Username dropdown clicked.");
            Thread.sleep(1000);
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(@class, 'css-') and text()='demouser']"))).click();
            System.out.println("Username 'demouser' selected.");

            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@id='password']//div[text()='Select Password']"))).click();
            System.out.println("Password dropdown clicked.");
            Thread.sleep(1000);
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(@class, 'css-') and text()='testingisfun99']"))).click();
            System.out.println("Password 'testingisfun99' selected.");

            driver.findElement(By.id("login-btn")).click();
            System.out.println("Login button clicked.");
            Thread.sleep(2000);
            System.out.println("Login process completed successfully.");

            // Step 3: Apply Samsung filter
            System.out.println("Filtering by the 'Samsung' vendor type");
            WebElement samsungFilter = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//input[@value='Samsung']/following-sibling::span[@class='checkmark' and text()='Samsung']")));
            samsungFilter.click();
            Thread.sleep(2000);
            System.out.println("Clicked on 'Samsung' filter.");

            // Step 4: Add to favourites
            String productName = "Galaxy S20";
            boolean found = false;

            List<WebElement> products = driver.findElements(By.className("shelf-item"));
            for (WebElement product : products) {
                if (product.getText().contains(productName)) {
                    try {
                        WebElement favBtn = product.findElement(By.className("shelf-stopper"));
                        favBtn.click();
                        System.out.println("Clicked 'Add to Favourites' for product: " + productName);
                        found = true;
                        break;
                    } catch (Exception e) {
                        System.out.println("Found product '" + productName + "' but couldn't click. Error: " + e.getMessage());
                    }
                }
            }

            if (!found) {
                System.out.println("Product '" + productName + "' not found on the page.");
            }

            Thread.sleep(2000);

            // Step 5: Check favourites list
            driver.findElement(By.id("favourites")).click();
            Thread.sleep(2000);
            System.out.println("The 'Favourites' page is displayed");

            List<WebElement> favProducts = driver.findElements(By.className("shelf-item"));
            boolean favFound = false;

            for (WebElement fav : favProducts) {
                if (fav.getText().contains(productName)) {
                    System.out.println("Success: The product '" + productName + "' is found in the Favourites list.");
                    favFound = true;
                    break;
                }
            }

            if (!favFound) {
                System.out.println("Error: The product '" + productName + "' was not found in the Favourites list.");
            }

            System.out.println("Automation testing completed.");

        } catch (Exception e) {
            System.out.println("Error during testFavouriteFlow: " + e.getMessage());
        }
    }
}
