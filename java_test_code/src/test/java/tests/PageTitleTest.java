package tests;

import runners.BstackRunner;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;

import static org.junit.jupiter.api.Assertions.assertTrue;

@TestMethodOrder(OrderAnnotation.class)
public class PageTitleTest extends BstackRunner {

    @Test
    @Order(1)
    public void testPageTitle() {
        System.out.println("Opening website...");
        driver.get("https://kolkata.bugbash.live/");

        String title = driver.getTitle();
        System.out.println("Title: " + title);

        try {
            assertTrue(title.contains("StackDemo"), "Title does not contain 'StackDemo'");
            System.out.println("Title is correct and contains 'StackDemo'");
        } catch (AssertionError e) {
            System.out.println("Title mismatch!");
            throw e; // rethrow so test fails in JUnit report
        }
    }
}
