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

public class TestCase08 {
    @Test
    public void TestCase08() {
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

        // Step 5. Click on 'REORDER'
        WebElement reOrder = driver.findElement(By.className("link-reorder"));
        reOrder.click();

        // Step 6. Change QTY & click Update and verify Grand Total is changed
        String oldQuantity = "1";
        String newQuantity = "10";

        ShoppingPage page = new ShoppingPage(driver);
        String oldPrice = page.getTotalPrice(oldQuantity);
        String newPrice = page.getTotalPrice(newQuantity);

        driver = page.getDriver();
        assertNotEquals(oldPrice, newPrice);
        ScreenshotTaker.takeScreenshot(driver, "TestCase08/Test01.png");

        // Step 7. Click on "Proceed to Checkout" and enter the information
        driver.findElement(By.className("btn-proceed-checkout")).click();

        String country = "United States", state = "New York", zip = "9000", address = "d",
                city = "e", telephone = "000000001";
        CheckOut check = new CheckOut(driver);
        driver = check.enterRequiredField(address, city, state, zip, country, telephone);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        WebElement message = wait.until(ExpectedConditions.elementToBeClickable(By.className("sub-title")));
        String orderId = message.getText();

        // Step 8. Verify Order is generated. Note the order number
        assertEquals("THANK YOU FOR YOUR PURCHASE!", orderId);
        ScreenshotTaker.takeScreenshot(driver, "TestCase08/Test02.png");

        // Finally: Close the driver
        driver.close();
    }
}
