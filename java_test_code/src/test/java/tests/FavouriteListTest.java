package tests;

import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.By;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import runners.BstackRunner;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class FavouriteListTest extends BstackRunner {

    @Test
    @Order(1)
    public void testFavouriteList() throws InterruptedException {
        System.out.println("Automation test starts");
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        // Step 1: Open the website
        driver.get("https://kolkata.bugbash.live");
        Thread.sleep(2000);
        System.out.println("Website homepage loaded successfully.");

        // Step 2: Click Sign In
        driver.findElement(By.id("Sign In")).click();
        Thread.sleep(2000);
        System.out.println("Sign In page displayed.");

        // Step 3: Select Username
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@id='username']//div[text()='Select Username']"))).click();
        System.out.println("Username dropdown clicked.");
        Thread.sleep(1000);

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(@class, 'css-') and text()='fav_user']"))).click();
        System.out.println("Username 'fav_user' selected.");
        Thread.sleep(1000);

        // Step 4: Select Password
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@id='password']//div[text()='Select Password']"))).click();
        System.out.println("Password dropdown clicked.");
        Thread.sleep(1000);

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(@class, 'css-') and text()='testingisfun99']"))).click();
        System.out.println("Password 'testingisfun99' selected.");
        Thread.sleep(1000);

        // Step 5: Click Login
        driver.findElement(By.id("login-btn")).click();
        System.out.println("Login button clicked.");
        Thread.sleep(2000);

        System.out.println("Login process completed successfully.");

        List<String> favoritedProductNames = new ArrayList<>();

        System.out.println("Identifying product cards...");
        List<WebElement> productCards = driver.findElements(By.className("shelf-item"));

        if (productCards.isEmpty()) {
            System.out.println("No product cards found on the page to inspect.");
            return;
        }

        System.out.println("Found " + productCards.size() + " product cards. Checking if added to favourites...");

        for (int i = 0; i < productCards.size(); i++) {
            WebElement card = productCards.get(i);
            try {
                WebElement productNameElement = card.findElement(By.className("shelf-item__title"));
                String productName = productNameElement.getText().trim();

                WebElement favouriteIconWrapper = card.findElement(By.className("shelf-stopper"));
                WebElement heartPathElement = favouriteIconWrapper.findElement(By.tagName("path"));
                String currentFillColor = heartPathElement.getAttribute("fill");

                if (currentFillColor != null && (currentFillColor.equalsIgnoreCase("#ff0000") ||
                        currentFillColor.equalsIgnoreCase("red") ||
                        currentFillColor.equalsIgnoreCase("rgb(255, 0, 0)"))) {
                    favoritedProductNames.add(productName);
                }
            } catch (NoSuchElementException e) {
                System.out.println("Warning: Could not find product name or favorite icon for a product card (index " + i + "). Skipping this card.");
            } catch (Exception e) {
                System.out.println("An unexpected error occurred while processing card " + i + ": " + e.getMessage());
            }
        }

        if (!favoritedProductNames.isEmpty()) {
            System.out.println("\n--- Products with Red Hearts ---");
            for (String product : favoritedProductNames) {
                System.out.println("- " + product);
            }
        } else {
            System.out.println("\nNo products found with red hearts.");
        }

        System.out.println("Automation test ends: Finished checking for red-hearted products.");
    }
}
