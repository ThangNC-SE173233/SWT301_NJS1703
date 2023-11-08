package assignment;

import driver.driverFactory;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pom.*;
import screenshot.ScreenshotTaker;

import static org.junit.jupiter.api.Assertions.*;

public class TestCase02 {
    // Test Steps:
    @Test
    public void TestCase02() {
        // Step 1. Go to http://live.techpanda.org/
        WebDriver driver = driverFactory.getChromeDriver();
        driver.get("http://live.techpanda.org/");

        // Step 2. Click on -> MOBILE -> menu
        HomePage home = new HomePage(driver);
        driver = home.goTo("mobile"); // Webpage is now http://live.techpanda.org/index.php/mobile.html

        // Step 3. In the list of all mobile , read the cost of Sony Xperia mobile (which is $100)
        MobilePage mp = new MobilePage(driver);
        String price = mp.getPrice("sony");
        float test_value = Float.parseFloat(price.replaceAll("[^\\d.]+", ""));

        // Step 4. Click on Sony Xperia mobile
        driver = mp.goTo("sony");

        // Step 5. Read the Sony Xperia mobile from detail page.
        CartPage cp = new CartPage(driver);
        float actual_value = cp.getPrice("grand");

        // Step 6. Compare Product value in list and details page should be equal ($100).
        ScreenshotTaker.takeScreenshot(driver, "TestCase02/Test01.png");
        assertEquals(test_value, actual_value);

        // Finally: Close the driver
        driver.close();
    }
}
