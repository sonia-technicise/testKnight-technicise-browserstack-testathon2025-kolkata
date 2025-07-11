package tests;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;
import runners.BstackRunner;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class InvalidLoginTest extends BstackRunner {

    @Test
    void loginWithInvalidUsername() {
        try {
            driver.get("https://kolkata.bugbash.live/signin");

            // Select username dropdown
            WebElement usernameDropdown = wait.until(ExpectedConditions.elementToBeClickable(
                    By.cssSelector("#username .css-yk16xz-control")));
            usernameDropdown.click();

            // Enter invalid username
            WebElement usernameInput = wait.until(ExpectedConditions.presenceOfElementLocated(
                    By.cssSelector("#username input")));
            usernameInput.sendKeys("atanu_user");
            usernameInput.sendKeys(Keys.ENTER);

            // Select password dropdown
            WebElement passwordDropdown = wait.until(ExpectedConditions.elementToBeClickable(
                    By.cssSelector("#password .css-yk16xz-control")));
            passwordDropdown.click();

            // Enter valid password
            WebElement passwordInput = wait.until(ExpectedConditions.presenceOfElementLocated(
                    By.cssSelector("#password input")));
            passwordInput.sendKeys("testingisfun99", Keys.ENTER);

            // 5. Locate and scroll to login button
            WebElement loginBtn = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("login-btn")));
            ((JavascriptExecutor) driver).executeScript(
                    "arguments[0].scrollIntoView({block: 'center'});", loginBtn);

            // 6. Wait and click login
            wait.until(ExpectedConditions.elementToBeClickable(loginBtn));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", loginBtn);

            // Wait for error toast/snackbar
            WebElement errorMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.cssSelector("h3.api-error")));
            String errorText = errorMsg.getText();
            System.out.println("Login error: " + errorText);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void loginWithInvalidPassword() {
        try {
            driver.get("https://kolkata.bugbash.live/signin");

            // Select username dropdown
            WebElement usernameDropdown = wait.until(ExpectedConditions.elementToBeClickable(
                    By.cssSelector("#username .css-yk16xz-control")));
            usernameDropdown.click();

            // Enter valid username
            WebElement usernameInput = wait.until(ExpectedConditions.presenceOfElementLocated(
                    By.cssSelector("#username input")));
            usernameInput.sendKeys("demouser");
            usernameInput.sendKeys(Keys.ENTER);

            // Select password dropdown
            WebElement passwordDropdown = wait.until(ExpectedConditions.elementToBeClickable(
                    By.cssSelector("#password .css-yk16xz-control")));
            passwordDropdown.click();

            // Enter invalid password
            WebElement passwordInput = wait.until(ExpectedConditions.presenceOfElementLocated(
                    By.cssSelector("#password input")));
            passwordInput.sendKeys("wrongpassword123", Keys.ENTER);

            // 5. Locate and scroll to login button
            WebElement loginBtn = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("login-btn")));
            ((JavascriptExecutor) driver).executeScript(
                    "arguments[0].scrollIntoView({block: 'center'});", loginBtn);

            // 6. Wait and click login
            wait.until(ExpectedConditions.elementToBeClickable(loginBtn));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", loginBtn);

            // Wait for error message
            WebElement errorMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.cssSelector("h3.api-error")));
            String errorText = errorMsg.getText();
            System.out.println("Login error: " + errorText);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void loginWithEmptyUsername() {
        try {
            driver.get("https://kolkata.bugbash.live/signin");

            // Select password dropdown
            WebElement passwordDropdown = wait.until(ExpectedConditions.elementToBeClickable(
                    By.cssSelector("#password .css-yk16xz-control")));
            passwordDropdown.click();

            // Enter valid password
            WebElement passwordInput = wait.until(ExpectedConditions.presenceOfElementLocated(
                    By.cssSelector("#password input")));
            passwordInput.sendKeys("testingisfun99", Keys.ENTER);

            // 5. Locate and scroll to login button
            WebElement loginBtn = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("login-btn")));
            ((JavascriptExecutor) driver).executeScript(
                    "arguments[0].scrollIntoView({block: 'center'});", loginBtn);

            // 6. Wait and click login
            wait.until(ExpectedConditions.elementToBeClickable(loginBtn));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", loginBtn);

            // Wait for error toast/snackbar
            WebElement errorMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.cssSelector("h3.api-error")));
            String errorText = errorMsg.getText();
            System.out.println("Login error: " + errorText);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void loginWithEmptyPassword() {
        try {
            driver.get("https://kolkata.bugbash.live/signin");

            // Select username dropdown
            WebElement usernameDropdown = wait.until(ExpectedConditions.elementToBeClickable(
                    By.cssSelector("#username .css-yk16xz-control")));
            usernameDropdown.click();

            // Enter valid username
            WebElement usernameInput = wait.until(ExpectedConditions.presenceOfElementLocated(
                    By.cssSelector("#username input")));
            usernameInput.sendKeys("demouser");
            usernameInput.sendKeys(Keys.ENTER);

            // 5. Locate and scroll to login button
            WebElement loginBtn = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("login-btn")));
            ((JavascriptExecutor) driver).executeScript(
                    "arguments[0].scrollIntoView({block: 'center'});", loginBtn);

            // 6. Wait and click login
            wait.until(ExpectedConditions.elementToBeClickable(loginBtn));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", loginBtn);

            // Wait for error toast/snackbar
            WebElement errorMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.cssSelector("h3.api-error")));
            String errorText = errorMsg.getText();
            System.out.println("Login error: " + errorText);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
