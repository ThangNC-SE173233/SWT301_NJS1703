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

public class TestCase06 {
    @Test
    public void TestCase06() {
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

        // (Sidestep): Add LG LCD to your wish list
        WebElement TVlink = driver.findElement(By.xpath("//a[normalize-space()='TV']"));
        TVlink.click(); // Webpage is now http://live.techpanda.org/index.php/tv.html

        WebElement lg = driver.findElement(By.xpath("//div[h2/a/@title='LG LCD']//div[@class='actions']//ul[@class='add-to-links']//li[1]//a[1]"));
        lg.click();

        // Step 5. In next page, Click ADD TO CART link
        WebElement btnAddCart = driver.findElement(By.xpath("//button[@title='Add to Cart']"));
        btnAddCart.click();

        // Step 6. Enter general shipping country, state/province and zip , then click Estimate
        String country = "United States";
        String state = "New York";
        String zip = "9000";

        CartPage cartPage = new CartPage(driver);
        driver = cartPage.estimate(country, state, zip);

        // Step 7. Verify Shipping cost generated
        WebElement shipping = null;
        shipping = driver.findElement(By.xpath("//label[@for='s_method_flatrate_flatrate']//span[@class='price']"));

        assertNotNull(shipping);
        ScreenshotTaker.takeScreenshot(driver, "TestCase06/Test01.png");

        // Step 8. Select Shipping Cost, Update Total
        String expectedShip = shipping.getText();

        WebElement comboFR = driver.findElement(By.id("s_method_flatrate_flatrate"));
        comboFR.click();

        WebElement updateTotal = driver.findElement(By.xpath("//button[@title='Update Total']"));
        updateTotal.click();

        shipping = driver.findElement(By.xpath("//*[@id='shopping-cart-totals-table']//tbody//tr[2]//td[2]//span[@class='price']"));
        String actualShip = shipping.getText();

        // Step 9. Verify shipping cost is added to total
        assertEquals(expectedShip, actualShip);
        ScreenshotTaker.takeScreenshot(driver, "TestCase06/Test02.png");

        // Step 10. Click "Proceed to Checkout"
        WebElement btnCheckout = driver.findElement(By.className("btn-checkout"));
        btnCheckout.click();

        // Step 11. Enter all information, and click Continue until you can click "Place Order"
        CheckOut check = new CheckOut(driver);
        driver = check.enterRequiredField("d", "e", state, zip, country, "000000001");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        WebElement message = wait.until(ExpectedConditions.elementToBeClickable(By.className("sub-title")));
        String orderId = message.getText();

        // Step 12. Verify Order is generated. Note the order number
        assertEquals("THANK YOU FOR YOUR PURCHASE!", orderId);
        ScreenshotTaker.takeScreenshot(driver, "TestCase06/Test03.png");

        // Finally: Close the driver
        driver.close();
    }
}
