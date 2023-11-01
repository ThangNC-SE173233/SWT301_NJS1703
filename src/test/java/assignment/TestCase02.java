package assignment;

import driver.driverFactory;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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
        WebElement MOBILElink = driver.findElement(By.className("first"));
        MOBILElink.click(); // Webpage is now http://live.techpanda.org/index.php/mobile.html

        // Step 3. In the list of all mobile , read the cost of Sony Xperia mobile (which is $100)
        WebElement sony = driver.findElement(By.xpath("//div[h2/a/@title='Sony Xperia']"));
        String test_value = sony.findElement(By.className("price")).getText();

        // Step 4. Click on Sony Xperia mobile
        sony = driver.findElement(By.xpath("//h2[a/@title='Sony Xperia']"));
        sony.click();

        // Step 5. Read the Sony Xperia mobile from detail page.
        WebElement sony_detail = driver.findElement(By.className("price"));
        String actual_value = sony_detail.getText();

        // Step 6. Compare Product value in list and details page should be equal ($100).
        assertEquals(test_value, actual_value);
        ScreenshotTaker.takeScreenshot(driver, "TestCase02/Test01.png");

        // Finally: Close the driver
        driver.close();
    }
}
