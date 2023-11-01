package assignment;

import driver.driverFactory;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
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
        WebElement title = driver.findElement(By.className("page-title"));
        assertEquals("THIS IS DEMO SITE FOR   ", title.getText());
        ScreenshotTaker.takeScreenshot(driver, "TestCase01/Test01.png");

        // Step 3. Click on -> MOBILE -> menu
        WebElement MOBILElink = driver.findElement(By.className("first"));
        MOBILElink.click(); // Webpage is now http://live.techpanda.org/index.php/mobile.html

        // Step 4. In the list of all mobile , select SORT BY -> dropdown as name
        WebElement dropDown = driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[2]/div[1]/div[2]/div[1]/div[3]/div[1]/div[1]/div[1]/select[1]"));
        Select select = new Select(dropDown);
        select.selectByVisibleText("Name");

        // Step 5. Verify all products are sorted by name
        List<WebElement> elements = driver.findElements(By.className("product-name"));
        List<String> actual_names = new ArrayList<>();
        List<String> test_names = new ArrayList<>();

        for (WebElement e : elements) {
            WebElement subE = e.findElement(By.xpath("./child::*"));
            actual_names.add(subE.getText());
            test_names.add(subE.getText());
        }

        Collections.sort(test_names);
        assertEquals(test_names, actual_names);
        ScreenshotTaker.takeScreenshot(driver, "TestCase01/Test02.png");

        // Finally: Close the driver
        driver.close();
    }

}
