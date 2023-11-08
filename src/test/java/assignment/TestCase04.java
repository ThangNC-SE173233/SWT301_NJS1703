package assignment;

import driver.driverFactory;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pom.*;
import screenshot.ScreenshotTaker;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class TestCase04 {
    @Test
    public void TestCase04() {
        // Step 1. Go to http://live.techpanda.org/
        WebDriver driver = driverFactory.getChromeDriver();
        driver.get("http://live.techpanda.org/");

        String mainWindow = driver.getWindowHandle();

        // Step 2. Click on -> MOBILE -> menu
        HomePage home = new HomePage(driver);
        driver = home.goTo("mobile"); // Webpage is now http://live.techpanda.org/index.php/mobile.html

        // Step 3. In mobile products list , click on Add To Compare for 2 mobiles (Sony Xperia & Iphone)
        ArrayList<String> test_names = new ArrayList<>();
        MobilePage mp = new MobilePage(driver);

        test_names.add(mp.getProductName("sony"));
        mp.addToCompare("sony");

        test_names.add(mp.getProductName("iphone"));
        mp.addToCompare("iphone");

        // Step 4. Click on COMPARE button. A popup window opens
        driver = mp.goTo("compare");

        // Step 5. Verify the pop-up window and check that the products are reflected in it
        for (String handle: driver.getWindowHandles()) {
            driver.switchTo().window(handle);
        }

        ArrayList<String> actual_names;
        ProductComparison pc = new ProductComparison(driver);
        actual_names = pc.getProductNames();
        driver = pc.getDriver();

        ScreenshotTaker.takeScreenshot(driver, "TestCase04/Test01.png");
        assertEquals(test_names, actual_names);

        // Finally: Close all popups and the driver
        driver.close();
        driver.switchTo().window(mainWindow);
        driver.close();
    }
}
