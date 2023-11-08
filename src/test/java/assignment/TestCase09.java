package assignment;

import driver.driverFactory;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pom.*;
import screenshot.ScreenshotTaker;

import static org.junit.jupiter.api.Assertions.*;

public class TestCase09 {
    @Test
    public void TestCase09() {
        // Step 1. Go to http://live.techpanda.org/
        WebDriver driver = driverFactory.getChromeDriver();
        driver.get("http://live.techpanda.org/");

        // Step 2. Go to Mobile and add IPHONE to cart
        WebElement MOBILElink = driver.findElement(By.className("first"));
        MOBILElink.click();

        WebElement iphone = driver.findElement(By.xpath("//div[h2/a/@title='IPhone']//div[@class='actions']//button"));
        iphone.click();

        // Step 3. Enter Coupon Code (GURU50)
        String discount_code = "GURU50";
        CartPage cartPage = new CartPage(driver);
        String message = cartPage.applyCoupon(discount_code);

        assertTrue(message.equalsIgnoreCase("Coupon code \"GURU50\" was applied."));

        // Step 4. Verify the discount generated
        float subTotal = cartPage.getPrice("sub");
        float discount = cartPage.getPrice("discount");
        float grandTotal = cartPage.getPrice("grand");

        driver = cartPage.getDriver();
        ScreenshotTaker.takeScreenshot(driver, "TestCase09/Test01.png");

        assertEquals(subTotal - discount, grandTotal);

        // Finally: Close the driver
        driver.close();
    }
}
