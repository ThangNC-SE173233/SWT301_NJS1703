package assignment;

import driver.driverFactory;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static org.junit.jupiter.api.Assertions.*;

public class TestCase03 {
    @Test
    public void TestCase03_Failed() {
        // Step 1. Go to http://live.techpanda.org/
        WebDriver driver = driverFactory.getChromeDriver();
        driver.get("http://live.techpanda.org/");

        // Step 2. Click on -> MOBILE -> menu
        WebElement MOBILElink = driver.findElement(By.className("first"));
        MOBILElink.click(); // Webpage is now http://live.techpanda.org/index.php/mobile.html

        // Step 3. In the list of all mobile , click on ADD TO CART for Sony Xperia mobile
        WebElement sony = driver.findElement(By.xpath("//div[h2/a/@title='Sony Xperia']"));
        sony.findElement(By.className("btn-cart"));
        sony.click();

        // Step 4. Change QTY value to 1000 and click UPDATE button. Expected that an error is displayed
        WebElement qty = driver.findElement(By.xpath("//input[@title='Qty']"));
        qty.clear();
        qty.sendKeys("1000");

        WebElement update = driver.findElement(By.xpath("//button[@title='Update']"));
        update.click();

        WebElement message = driver.findElement(By.className("error"));

        // Step 5. Verify the error message
        String actual_error = message.getText();
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
        WebElement MOBILElink = driver.findElement(By.className("first"));
        MOBILElink.click(); // Webpage is now http://live.techpanda.org/index.php/mobile.html

        // Step 3. In the list of all mobile , click on ADD TO CART for Sony Xperia mobile
        WebElement sony = driver.findElement(By.xpath("//div[h2/a/@title='Sony Xperia']"));
        sony.findElement(By.className("btn-cart"));
        sony.click();

        // Step 6. Then click on EMPTY CART link in the footer of list of all mobiles
        WebElement empty = driver.findElement(By.id("empty_cart_button"));
        empty.click();

        // Step 7. Verify that a message "SHOPPING CART IS EMPTY" is shown.
        WebElement header = driver.findElement(By.className("page-title"));
        String actual_message = header.findElement(By.xpath("./child::*")).getText();
        assertEquals("SHOPPING CART IS EMPTY", actual_message);

        // Finally: Close the driver
        driver.close();
    }
}
