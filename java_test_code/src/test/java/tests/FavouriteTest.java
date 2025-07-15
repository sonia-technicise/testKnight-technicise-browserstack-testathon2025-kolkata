package tests;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import runners.BstackRunner;

import java.util.List;

@TestMethodOrder(OrderAnnotation.class)
public class FavouriteTest extends BstackRunner {

    @Test
    @Order(1)
    public void test_open_website() {
        System.out.println("Automation testing starts");
        driver.get("https://kolkata.bugbash.live/");
        System.out.println("The website's homepage loads successfully.");
    }

    @Test
    @Order(2)
    public void test_sign_in() {
        try {
            driver.findElement(By.id("Sign In")).click();
            Thread.sleep(2000);
            System.out.println("Sign In page displayed.");

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
        } catch (Exception e) {
            System.out.println("Error during sign in: " + e.getMessage());
        }
    }

    @Test
    @Order(3)
    public void test_apply_filter() {
        try {
            System.out.println("Filtering by the 'Samsung' vendor type");
            WebElement samsungFilter = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//input[@value='Samsung']/following-sibling::span[@class='checkmark' and text()='Samsung']")));
            samsungFilter.click();
            Thread.sleep(2000);
            System.out.println("Clicked on 'Samsung' filter.");
        } catch (Exception e) {
            System.out.println("Could not click 'Samsung' filter. Error: " + e.getMessage());
        }
    }

    @Test
    @Order(4)
    public void test_add_to_favs() {
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

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            // ignore
        }
    }

    @Test
    @Order(5)
    public void test_check_fav_list() {
        String productName = "Galaxy S20";

        try {
            driver.findElement(By.id("favourites")).click();
            Thread.sleep(2000);
            System.out.println("The 'Favourites' page is displayed");

            List<WebElement> favProducts = driver.findElements(By.className("shelf-item"));
            boolean found = false;

            for (WebElement fav : favProducts) {
                if (fav.getText().contains(productName)) {
                    System.out.println("Success: The product '" + productName + "' is found in the Favourites list.");
                    found = true;
                    break;
                }
            }

            if (!found) {
                System.out.println("Error: The product '" + productName + "' was not found in the Favourites list.");
            }

        } catch (Exception e) {
            System.out.println("An error occurred while checking the Favourites list: " + e.getMessage());
        }
    }
}
