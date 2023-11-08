package assignment;

import driver.driverFactory;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pom.*;
import screenshot.ScreenshotTaker;

import static org.junit.jupiter.api.Assertions.*;

public class TestCase03 {
    @Test
    public void TestCase03_Failed() {
        // Step 1. Go to http://live.techpanda.org/
        WebDriver driver = driverFactory.getChromeDriver();
        driver.get("http://live.techpanda.org/");

        // Step 2. Click on -> MOBILE -> menu
        HomePage home = new HomePage(driver);
        driver = home.goTo("mobile"); // Webpage is now http://live.techpanda.org/index.php/mobile.html

        // Step 3. In the list of all mobile , click on ADD TO CART for Sony Xperia mobile
        MobilePage mp = new MobilePage(driver);
        driver = mp.goTo("sony");

        // Step 4. Change QTY value to 1000 and click UPDATE button. Expected that an error is displayed
        String value = "1000";
        CartPage cp = new CartPage(driver);
        String actual_error = cp.getErrorMessage(value);

        // Step 5. Verify the error message
        ScreenshotTaker.takeScreenshot(driver, "TestCase03/Test01.png");
        assertEquals("The requested quantity for \"Sony Xperia\" is not available.", actual_error);

        // Finally: Close the driver
        driver.close();
    }
    @Test
    public void TestCase03_Success() {
        // Step 1. Go to http://live.techpanda.org/
        WebDriver driver = driverFactory.getChromeDriver();
        driver.get("http://live.techpanda.org/");

        // Step 2. Click on -> MOBILE -> menu
        HomePage home = new HomePage(driver);
        driver = home.goTo("mobile"); // Webpage is now http://live.techpanda.org/index.php/mobile.html

        // Step 3. In the list of all mobile , click on ADD TO CART for Sony Xperia mobile
        MobilePage mp = new MobilePage(driver);
        driver = mp.goTo("sony");

        // Step 6. Then click on EMPTY CART link in the footer of list of all mobiles
        CartPage cp = new CartPage(driver);
        driver = cp.goTo("empty cart");

        // Step 7. Verify that a message "SHOPPING CART IS EMPTY" is shown.
        String actual_message = cp.getTitle();

        ScreenshotTaker.takeScreenshot(driver, "TestCase03/Test02.png");
        assertEquals("SHOPPING CART IS EMPTY", actual_message);

        // Finally: Close the driver
        driver.close();
    }
}
