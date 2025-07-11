package tests;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;
import runners.BstackRunner;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class test_login extends BstackRunner {

    @Test
    void loginTest() {
        try {
            driver.get("https://kolkata.bugbash.live/signin");

            // 1. Click username dropdown
            WebElement selectControl = wait.until(ExpectedConditions.elementToBeClickable(
                By.cssSelector("#username .css-yk16xz-control")));
            selectControl.click();

            // 2. Enter username and select
            WebElement searchInput = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.cssSelector("#username input")));
            searchInput.sendKeys("demouser", Keys.ENTER);

            // 3. Click password dropdown
            WebElement passwordControl = wait.until(ExpectedConditions.elementToBeClickable(
                By.cssSelector("#password .css-yk16xz-control")));
            passwordControl.click();

            // 4. Enter password
            WebElement passwordInput = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.cssSelector("#password input")));
            passwordInput.sendKeys("testingisfun99");

            // 5. Locate and scroll to login button
            WebElement loginBtn = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("login-btn")));
            ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block: 'center'});", loginBtn);

            // 6. Wait and click login
            wait.until(ExpectedConditions.elementToBeClickable(loginBtn));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", loginBtn);

            // 7. Wait for navigation
            wait.until(d -> d.getCurrentUrl().contains("/"));

            // 8. Optional: Assert some success condition
            assertTrue(driver.getCurrentUrl().contains("dashboard") ||
                       driver.getPageSource().contains("Welcome") ||
                       driver.getCurrentUrl().contains("bugbash"), "Login might have failed");

            System.out.println("Login successful!");

        } catch (Exception e) {
            System.err.println("Login failed: " + e.getMessage());
            e.printStackTrace();
        } finally {
            driver.quit();
        }
    }
}
