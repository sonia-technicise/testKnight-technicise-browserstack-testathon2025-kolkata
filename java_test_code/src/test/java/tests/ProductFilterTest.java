package tests;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import runners.BstackRunner;

import java.time.Duration;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ProductFilterTest extends BstackRunner {

    @Test
    @Order(1)
    void testProductFiltering() {
        System.out.println("[Test 1] Filtering vendors and toggling filters...");

        try {
            driver.get("https://kolkata.bugbash.live/");
            wait.until(ExpectedConditions.presenceOfElementLocated(By.className("filters")));
            System.out.println("-> Website's homepage loaded successfully.");
        } catch (Exception e) {
            throw new RuntimeException("Failed to load homepage: " + e.getMessage(), e);
        }

        filterToggle("Samsung");
        filterToggle("Apple");
        filterToggle("Google");
        filterToggle("OnePlus");
    }

    private void filterToggle(String brand) {
        System.out.println("Filtering by the '" + brand + "' vendor type");
        try {
            WebElement filterSpan = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//input[@value='" + brand + "']/following-sibling::span[@class='checkmark' and text()='" + brand + "']")));
            filterSpan.click();
            System.out.println("-> Clicked on '" + brand + "' filter.");
            Thread.sleep(2000);
        } catch (Exception e) {
            System.out.println("Could not click '" + brand + "' filter. Error: " + e.getMessage());
        }

        try {
            WebElement filterLabel = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//input[@value='" + brand + "']/parent::label")));
            filterLabel.click();
            System.out.println("-> Clicked on '" + brand + "' filter again (via label).\n");
            Thread.sleep(2000);
        } catch (Exception eLabel) {
            System.out.println("Could not click '" + brand + "' filter via label. Error: " + eLabel.getMessage());
        }
    }
}
