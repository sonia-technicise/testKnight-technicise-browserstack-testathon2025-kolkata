package tests;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;
import runners.BstackRunner;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoginTest extends BstackRunner {

    @Test
    void loadingSigninPage() {
        try {
            driver.get("https://kolkata.bugbash.live");

            WebElement signinBtn = wait.until(ExpectedConditions.elementToBeClickable(
                    By.id("Sign In")));
            signinBtn.click();

            wait.until(d -> d.getCurrentUrl().contains("/signin"));

            System.out.println("Navigate to signin page successful!");

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Test failed due to exception: " + e.getMessage(), e);
        }
    }

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
            throw new RuntimeException("Test failed due to exception: " + e.getMessage(), e);
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
            throw new RuntimeException("Test failed due to exception: " + e.getMessage(), e);
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
            throw new RuntimeException("Test failed due to exception: " + e.getMessage(), e);
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
            throw new RuntimeException("Test failed due to exception: " + e.getMessage(), e);
        }
    }

    @Test
    void loginTestWithDemoUser() {
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
            passwordInput.sendKeys("testingisfun99", Keys.ENTER);

            // 5. Locate and scroll to login button
            WebElement loginBtn = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("login-btn")));
            ((JavascriptExecutor) driver).executeScript(
                    "arguments[0].scrollIntoView({block: 'center'});", loginBtn);

            // 6. Wait and click login
            wait.until(ExpectedConditions.elementToBeClickable(loginBtn));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", loginBtn);

            // 7. Wait for navigation
            wait.until(d -> d.getCurrentUrl().contains("/"));

            System.out.println("Login successful!");

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Test failed due to exception: " + e.getMessage(), e);
        }
    }

    @Test
    void loginTestWithImageNotLoadingUser() {
        try {
            driver.get("https://kolkata.bugbash.live/signin");

            // 1. Click username dropdown
            WebElement selectControl = wait.until(ExpectedConditions.elementToBeClickable(
                    By.cssSelector("#username .css-yk16xz-control")));
            selectControl.click();

            // 2. Enter username and select
            WebElement searchInput = wait.until(ExpectedConditions.presenceOfElementLocated(
                    By.cssSelector("#username input")));
            searchInput.sendKeys("image_not_loading_user", Keys.ENTER);

            // 3. Click password dropdown
            WebElement passwordControl = wait.until(ExpectedConditions.elementToBeClickable(
                    By.cssSelector("#password .css-yk16xz-control")));
            passwordControl.click();

            // 4. Enter password
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

            // 7. Wait for navigation
            wait.until(d -> d.getCurrentUrl().contains("/"));

            System.out.println("Login successful!");

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Test failed due to exception: " + e.getMessage(), e);
        }
    }

    @Test
    void loginTestWithExistingOrdersUser() {
        try {
            driver.get("https://kolkata.bugbash.live/signin");

            // 1. Click username dropdown
            WebElement selectControl = wait.until(ExpectedConditions.elementToBeClickable(
                    By.cssSelector("#username .css-yk16xz-control")));
            selectControl.click();

            // 2. Enter username and select
            WebElement searchInput = wait.until(ExpectedConditions.presenceOfElementLocated(
                    By.cssSelector("#username input")));
            searchInput.sendKeys("existing_orders_user", Keys.ENTER);

            // 3. Click password dropdown
            WebElement passwordControl = wait.until(ExpectedConditions.elementToBeClickable(
                    By.cssSelector("#password .css-yk16xz-control")));
            passwordControl.click();

            // 4. Enter password
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

            // 7. Wait for navigation
            wait.until(d -> d.getCurrentUrl().contains("/"));

            System.out.println("Login successful!");

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Test failed due to exception: " + e.getMessage(), e);
        }
    }

    @Test
    void loginTestWithFavUser() {
        try {
            driver.get("https://kolkata.bugbash.live/signin");

            // 1. Click username dropdown
            WebElement selectControl = wait.until(ExpectedConditions.elementToBeClickable(
                    By.cssSelector("#username .css-yk16xz-control")));
            selectControl.click();

            // 2. Enter username and select
            WebElement searchInput = wait.until(ExpectedConditions.presenceOfElementLocated(
                    By.cssSelector("#username input")));
            searchInput.sendKeys("fav_user", Keys.ENTER);

            // 3. Click password dropdown
            WebElement passwordControl = wait.until(ExpectedConditions.elementToBeClickable(
                    By.cssSelector("#password .css-yk16xz-control")));
            passwordControl.click();

            // 4. Enter password
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

            // 7. Wait for navigation
            wait.until(d -> d.getCurrentUrl().contains("/"));

            System.out.println("Login successful!");

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Test failed due to exception: " + e.getMessage(), e);
        }
    }

    @Test
    void loginTestWithLockedUser() {
        try {
            driver.get("https://kolkata.bugbash.live/signin");

            // 1. Click username dropdown
            WebElement selectControl = wait.until(ExpectedConditions.elementToBeClickable(
                    By.cssSelector("#username .css-yk16xz-control")));
            selectControl.click();

            // 2. Enter username and select
            WebElement searchInput = wait.until(ExpectedConditions.presenceOfElementLocated(
                    By.cssSelector("#username input")));
            searchInput.sendKeys("locked_user", Keys.ENTER);

            // 3. Click password dropdown
            WebElement passwordControl = wait.until(ExpectedConditions.elementToBeClickable(
                    By.cssSelector("#password .css-yk16xz-control")));
            passwordControl.click();

            // 4. Enter password
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

            // 7. Wait for navigation
            wait.until(d -> d.getCurrentUrl().contains("/"));

            System.out.println("Login successful!");

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Test failed due to exception: " + e.getMessage(), e);
        }
    }

}
