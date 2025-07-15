package tests;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import runners.BstackRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AddToFavouritesTest extends BstackRunner {

    @Test
    @Order(1)
    void testLoginAndAddToFavourites() {
        try {
            System.out.println("[Test 1] Launching site and logging in...");

            driver.get("https://kolkata.bugbash.live/");
            wait.until(ExpectedConditions.presenceOfElementLocated(By.className("filters")));
            System.out.println("-> Homepage loaded.");

            // ---- Sign In flow ----
            WebElement signInBtn = wait.until(ExpectedConditions.elementToBeClickable(By.id("Sign In")));
            signInBtn.click();
            Thread.sleep(2000);

            WebElement usernameDropdown = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//div[@id='username']//div[text()='Select Username']")));
            usernameDropdown.click();
            WebElement usernameOption = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//div[contains(@class, 'css-') and text()='demouser']")));
            usernameOption.click();

            WebElement passwordDropdown = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//div[@id='password']//div[text()='Select Password']")));
            passwordDropdown.click();
            WebElement passwordOption = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//div[contains(@class, 'css-') and text()='testingisfun99']")));
            passwordOption.click();

            WebElement loginBtn = driver.findElement(By.id("login-btn"));
            loginBtn.click();
            Thread.sleep(2000);
            System.out.println("-> Logged in successfully.");

            // ---- Add to Favourites ----
            String productNameToFind = "iPhone 12 Mini";
            boolean productFound = false;

            wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.className("shelf-item")));
            List<WebElement> products = driver.findElements(By.className("shelf-item"));

            for (WebElement product : products) {
                WebElement name = product.findElement(By.className("shelf-item__title"));
                if (name.getText().equalsIgnoreCase(productNameToFind)) {
                    WebElement favButton = product.findElement(By.className("shelf-stopper"));
                    favButton.click();
                    System.out.println("-> Clicked 'Add to Favourites' for: " + productNameToFind);
                    productFound = true;
                    break;
                }
            }

            if (!productFound) {
                throw new RuntimeException("Product '" + productNameToFind + "' not found on the page.");
            }

            Thread.sleep(2000);

            WebElement favPageBtn = driver.findElement(By.id("favourites"));
            favPageBtn.click();
            Thread.sleep(2000);
            System.out.println("-> Navigated to the Favourites page.");

        } catch (Exception e) {
            throw new RuntimeException("Favourites flow failed: " + e.getMessage(), e);
        }
    }
}