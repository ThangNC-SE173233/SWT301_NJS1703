package assignment;

import driver.driverFactory;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pom.*;
import screenshot.ScreenshotTaker;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

public class TestCase10 {
    @Test
    public void TestCase10() {
        // Step 1. Go to http://live.techpanda.org/index.php/backendlogin
        WebDriver driver = driverFactory.getChromeDriver();
        driver.get("http://live.techpanda.org/index.php/backendlogin");

        // Step 2. Login the credentials provided
        String id = "user01";
        String password = "guru99com";

        LoginAdmin la = new LoginAdmin(driver);
        la.login(id, password);

        driver = la.getDriver();

        // Step 3. Go to Sales-> Orders menu
        WebElement close = driver.findElement(By.xpath("//a[@title='close']"));
        close.click();

        WebElement sales = driver.findElement(By.xpath("//ul[@id='nav']//li[1]//a"));
        sales.click();

        WebElement order = driver.findElement(By.xpath("//ul[@id='nav']//li[1]//ul//li[1]//a"));
        order.click();

        // Step 4. Input OrderId and FromDate -> ToDate and click Search button
        String order_id = "100021148", order_from = "11/7/2023", order_to = "11/7/2023";
        Orders orders = new Orders(driver);
        orders.searchForOrder(order_id, order_from, order_to);

        driver = orders.getDriver();
        sales = driver.findElement(By.xpath("//ul[@id='nav']//li[1]//a"));
        sales.click();

        // Step 5. Screenshot capture.
        ScreenshotTaker.takeScreenshot(driver, "TestCase10/Test01.png");

        // Finally: Close the driver
        driver.close();
    }
}
