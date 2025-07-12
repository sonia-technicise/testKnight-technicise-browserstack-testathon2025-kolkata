package tests;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.ui.ExpectedConditions;

import runners.BstackRunner;

@TestMethodOrder(OrderAnnotation.class)
public class NavigationBarTest extends BstackRunner {

    @Test
    @Order(1)
    public void testNavigationBarFunctionality() {
        System.out.println("Automation testing starts");
        System.out.println("The web browser is opened successfully.");

        try {
            // Step 1: Navigate to the website
            driver.get("https://kolkata.bugbash.live/");
            Thread.sleep(2000);
            System.out.println("The website's homepage loads successfully.");

            // Step 2: Click Sign In
            driver.findElement(By.id("Sign In")).click();
            Thread.sleep(2000);
            System.out.println("The Sign In page is displayed with fields for username and password.");

            // Step 3: Login
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@id='username']//div[text()='Select Username']"))).click();
            System.out.println("Clicked 'Select Username' dropdown.");
            Thread.sleep(1000);
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(@class, 'css-') and text()='demouser']"))).click();
            System.out.println("Selected username: demouser");
            Thread.sleep(1000);
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@id='password']//div[text()='Select Password']"))).click();
            System.out.println("Clicked 'Select Password' dropdown.");
            Thread.sleep(1000);
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(@class, 'css-') and text()='testingisfun99']"))).click();
            System.out.println("Selected password: testingisfun99");
            Thread.sleep(1000);
            wait.until(ExpectedConditions.elementToBeClickable(By.id("login-btn"))).click();
            System.out.println("Clicked 'Log In' button.");
            Thread.sleep(2000);

            // Step 4: Offers button
            try {
                driver.findElement(By.id("offers")).click();
                Thread.sleep(2000);
                System.out.println("The 'Offers' page is displayed");
            } catch (NoSuchElementException e) {
                System.out.println("The 'Offers' button was not found on the page.");
            }

            // Back to Home via logo
            try {
                driver.findElement(By.className("Navbar_logo__26S5Y")).click();
                Thread.sleep(2000);
                System.out.println("Went back to HOME page");
            } catch (NoSuchElementException e) {
                System.out.println("The 'BrowserStack logo' was not found on the page.");
            }

            // Step 5: Orders button
            try {
                driver.findElement(By.id("orders")).click();
                Thread.sleep(2000);
                System.out.println("The 'Orders' page is displayed");
            } catch (NoSuchElementException e) {
                System.out.println("The 'Orders' button was not found on the page.");
            }

            // Back to Home via logo again
            try {
                driver.findElement(By.className("Navbar_logo__26S5Y")).click();
                Thread.sleep(2000);
                System.out.println("Went back to HOME page");
            } catch (NoSuchElementException e) {
                System.out.println("The 'BrowserStack logo' was not found on the page.");
            }

            // Step 6: Favourites button
            try {
                driver.findElement(By.id("favourites")).click();
                Thread.sleep(2000);
                System.out.println("The 'Favourites' page is displayed");
            } catch (NoSuchElementException e) {
                System.out.println("The 'Favourites' button was not found on the page.");
            }

        } catch (Exception e) {
            throw new RuntimeException("Navigation bar test failed: " + e.getMessage());
        }
    }
}
