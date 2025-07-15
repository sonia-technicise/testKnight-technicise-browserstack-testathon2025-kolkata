package tests;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;
import runners.BstackRunner;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class LogoutTest extends BstackRunner {

    @Test
    void logout() {
        try {
            driver.get("https://kolkata.bugbash.live/signin");
            System.out.println("Signin page loaded");


            // 1. Click username dropdown
            WebElement selectControl = wait.until(ExpectedConditions.elementToBeClickable(
                    By.cssSelector("#username .css-yk16xz-control")));
            selectControl.click();
            System.out.println("Username Dropdown Clicked");


            // 2. Enter username and select
            WebElement searchInput = wait.until(ExpectedConditions.presenceOfElementLocated(
                    By.cssSelector("#username input")));
            searchInput.sendKeys("demouser", Keys.ENTER);
            Thread.sleep(2000);
            System.out.println("Username Selected");

            // 3. Click password dropdown
            WebElement passwordControl = wait.until(ExpectedConditions.elementToBeClickable(
                    By.cssSelector("#password .css-yk16xz-control")));
            passwordControl.click();
            System.out.println("Password Dropdown Clicked");

            // 4. Enter password
            WebElement passwordInput = wait.until(ExpectedConditions.presenceOfElementLocated(
                    By.cssSelector("#password input")));
            passwordInput.sendKeys("testingisfun99", Keys.ENTER);
            System.out.println("Password Selected");
            Thread.sleep(2000);

            // 5. Locate and scroll to login button
            WebElement loginBtn = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("login-btn")));
            ((JavascriptExecutor) driver).executeScript(
                    "arguments[0].scrollIntoView({block: 'center'});", loginBtn);

            // 6. Wait and click login
            wait.until(ExpectedConditions.elementToBeClickable(loginBtn));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", loginBtn);
            System.out.println("Login Button Clicked");

            // 7. Wait for navigation
            wait.until(d -> d.getCurrentUrl().contains("/"));
            System.out.println("Login Successful!");

            Thread.sleep(2000);

            WebElement logoutBtn = wait.until(ExpectedConditions.elementToBeClickable(
                    By.id("logout")));
            logoutBtn.click();
            System.out.println("Logout Button Clicked");

            WebElement signInLink = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//a[text()='Sign In']")));

            if (signInLink.isDisplayed()) {
                System.out.println("Logout Successful!");
            } else {
                System.out.println("Logout Failed.");
            }

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Test failed due to exception: " + e.getMessage(), e);
        }
    }

}
