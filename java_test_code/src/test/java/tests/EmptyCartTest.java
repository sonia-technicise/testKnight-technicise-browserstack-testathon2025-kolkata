package tests;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import runners.BstackRunner;

import java.time.Duration;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class EmptyCartTest extends BstackRunner {

    @Test
    @Order(1)
    void testEmptyCartCheck() {
        System.out.println("[Test] Starting empty cart verification test...");

        try {
            driver.get("https://kolkata.bugbash.live/");
            wait.until(ExpectedConditions.presenceOfElementLocated(By.className("filters")));
            System.out.println("-> Homepage loaded successfully.");

            // Sign In
            WebElement signInBtn = driver.findElement(By.id("Sign In"));
            signInBtn.click();
            Thread.sleep(2000);
            System.out.println("-> Sign In page displayed.");

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

            WebElement loginButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("login-btn")));
            loginButton.click();
            Thread.sleep(2000);
            System.out.println("-> Logged in successfully.");

            // Open cart
            WebElement cartButton = driver.findElement(By.className("bag__quantity"));
            cartButton.click();
            Thread.sleep(1000);
            System.out.println("-> Cart opened.");

            // Check subtotal value
            WebElement subtotalElement = driver.findElement(By.className("sub-price"));
            String subtotalText = subtotalElement.getText().trim();

            if (subtotalText.equals("$ 0.00")) {
                System.out.println("-> Subtotal is correctly $ 0.00");
            } else {
                System.out.println("-> Subtotal is incorrect. Found: " + subtotalText);
            }

            // Check buy button label
            WebElement buyButton = driver.findElement(By.className("buy-btn"));
            String buyButtonText = buyButton.getText().trim();

            if (buyButtonText.equalsIgnoreCase("CONTINUE SHOPPING")) {
                System.out.println("-> The cart is empty: no 'Checkout' button available.");
            } else {
                System.out.println("-> Cart has items: Checkout button available.");
            }

        } catch (Exception e) {
            throw new RuntimeException("Empty cart test failed: " + e.getMessage(), e);
        }
    }
}
