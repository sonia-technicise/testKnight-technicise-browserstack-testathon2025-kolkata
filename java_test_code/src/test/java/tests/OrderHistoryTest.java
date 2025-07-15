package tests;

import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;
import static org.junit.jupiter.api.Assertions.*;

import runners.BstackRunner;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class OrderHistoryTest extends BstackRunner {

    @Test
    @Order(1)
    public void testOrderHistory() throws InterruptedException {
        System.out.println("Automation test starts");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        // Step 1: Open the website
        driver.get("https://kolkata.bugbash.live");
        Thread.sleep(2000);
        System.out.println("Website homepage loaded successfully.");

        // Step 2: Click Sign In
        try {
            WebElement signInButton = driver.findElement(By.id("Sign In"));
            signInButton.click();
            Thread.sleep(2000);
            System.out.println("Sign In page displayed.");
        } catch (NoSuchElementException e) {
            System.out.println("Sign In button not found.");
            fail("Cannot proceed without sign in.");
        }

        // Step 3: Select Username
        try {
            WebElement usernameDropdown = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@id='username']//div[text()='Select Username']")));
            usernameDropdown.click();
            System.out.println("Username dropdown clicked.");
            Thread.sleep(1000);

            WebElement usernameOption = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(@class, 'css-') and text()='existing_orders_user']")));
            usernameOption.click();
            System.out.println("Username 'existing_orders_user' selected.");
            Thread.sleep(1000);
        } catch (Exception e) {
            System.out.println("Failed to select username. Error: " + e.getMessage());
            fail("Username selection failed.");
        }

        // Step 4: Select Password
        try {
            WebElement passwordDropdown = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@id='password']//div[text()='Select Password']")));
            passwordDropdown.click();
            System.out.println("Password dropdown clicked.");
            Thread.sleep(1000);

            WebElement passwordOption = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(@class, 'css-') and text()='testingisfun99']")));
            passwordOption.click();
            System.out.println("Password 'testingisfun99' selected.");
            Thread.sleep(1000);
        } catch (Exception e) {
            System.out.println("Failed to select password. Error: " + e.getMessage());
            fail("Password selection failed.");
        }

        // Step 5: Click Login
        try {
            WebElement loginButton = driver.findElement(By.id("login-btn"));
            loginButton.click();
            System.out.println("Login button clicked.");
            Thread.sleep(2000);
            System.out.println("Login process completed successfully.");
        } catch (Exception e) {
            System.out.println("Login failed. Error: " + e.getMessage());
            fail("Login process failed.");
        }

        // Step 6: Click Orders
        try {
            WebElement ordersButton = driver.findElement(By.id("orders"));
            ordersButton.click();
            Thread.sleep(2000);
            System.out.println("The 'Orders' page is displayed.");
        } catch (NoSuchElementException e) {
            System.out.println("The 'Orders' button was not found on the page.");
            fail("Orders page not accessible.");
        } catch (Exception e) {
            System.out.println("Unexpected error while opening orders page: " + e.getMessage());
            fail("Could not access Orders page.");
        }

        // Step 7: Check if orders are present
        try {
            System.out.println("Checking for orders on the page.");
            java.util.List<WebElement> orderItems = driver.findElements(By.id("1"));
            if (!orderItems.isEmpty()) {
                System.out.println("Found " + orderItems.size() + " orders on the page.");
                assertTrue(orderItems.size() > 0, "Assertion Failed: Expected orders to be present.");
                System.out.println("Test Passed: Orders are present on the page.");
            } else {
                System.out.println("No orders found on the page.");
                fail("Assertion Failed: No orders found on the order history page for 'existing_orders_user'.");
            }
        } catch (Exception e) {
            System.out.println("Error while checking orders: " + e.getMessage());
            fail("Order check failed due to error.");
        }

        System.out.println("Automation test ends.");
    }
}
