package assignment;

import driver.driverFactory;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import pom.*;
import screenshot.ScreenshotTaker;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TestCase01 {
    // Test Steps:
    @Test
    public void TestCase01() {
        // Step 1. Go to http://live.techpanda.org/
        WebDriver driver = driverFactory.getChromeDriver();
        driver.get("http://live.techpanda.org/");

        // Step 2. Verify Title of the page
        HomePage home = new HomePage(driver);

        ScreenshotTaker.takeScreenshot(driver, "TestCase01/Test01.png");
        assertEquals("THIS IS DEMO SITE FOR   ", home.getTitle());

        // Step 3. Click on -> MOBILE -> menu
        driver = home.goTo("mobile"); // Webpage is now http://live.techpanda.org/index.php/mobile.html

        // Step 4. In the list of all mobile , select SORT BY -> dropdown as name
        List<String> test_names;
        List<String> actual_names;

        MobilePage mp = new MobilePage(driver);

        test_names = mp.getProductNames(false);
        Collections.sort(test_names);

        actual_names = mp.getProductNames(true);
        driver = mp.getDriver();

        // Step 5. Verify all products are sorted by name
        ScreenshotTaker.takeScreenshot(driver, "TestCase01/Test02.png");
        assertEquals(test_names, actual_names);

        // Finally: Close the driver
        driver.close();
    }

}
