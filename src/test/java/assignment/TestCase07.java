package assignment;

import driver.driverFactory;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pom.*;
import screenshot.ScreenshotTaker;

import static org.junit.jupiter.api.Assertions.*;

public class TestCase07 {
    @Test
    public void TestCase07() {
        // Step 1. Go to http://live.techpanda.org/
        WebDriver driver = driverFactory.getChromeDriver();
        driver.get("http://live.techpanda.org/");

        // Step 2. Click on my account link
        WebElement accountlink = driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account'][normalize-space()='My Account']"));
        accountlink.click(); // Webpage is now http://live.techpanda.org/index.php/customer/account/login/

        // Step 3. Login in application using previously created credential
        String email = "demo@example.com";
        String password = "000000";

        Login login = new Login(driver);
        driver = login.signIn(email, password);

        // Step 4. Click on 'My Orders'
        WebElement myOrders = driver.findElement(By.xpath("//a[@href='http://live.techpanda.org/index.php/sales/order/history/']"));
        myOrders.click();

        // Step 5. Click on 'View Order'
        WebElement thisOrder = driver.findElement(By.xpath("//a[contains(text(),'View Order')]"));
        thisOrder.click();

        // Step 6. Click on 'Print Order' link
        WebElement printOrder = driver.findElement(By.className("link-print"));
        printOrder.click();

        String mainWindow = driver.getWindowHandle();

        for (String handle: driver.getWindowHandles()) {
            driver.switchTo().window(handle);
        }

        String actualURL = driver.getCurrentUrl();
        assertTrue(actualURL.startsWith("http://live.techpanda.org/index.php/sales/order/print/order_id/"));
        ScreenshotTaker.takeScreenshot(driver, "TestCase07/Test01.png");

        // Finally: Close all popups and the driver
        driver.close();
        driver.switchTo().window(mainWindow);
        driver.close();
    }
}
