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
        HomePage home = new HomePage(driver);
        home.goTo("my account"); // Webpage is now http://live.techpanda.org/index.php/customer/account/login/

        // Step 3. Login in application using previously created credential
        String email = "demo@example.com";
        String password = "000000";

        Login login = new Login(driver);
        driver = login.signIn(email, password);

        // (Sidestep): Add LG LCD to your wish list
        Dashboard dash = new Dashboard(driver);
        driver = dash.goTo("tv"); // Webpage is now http://live.techpanda.org/index.php/tv.html

        TVPage tv = new TVPage(driver);

        // Step 5. In next page, Click ADD TO CART link
        driver = tv.goTo("lg");

        // Step 6. Enter general shipping country, state/province and zip , then click Estimate
        String country = "United States";
        String state = "New York";
        String zip = "9000";

        CartPage cartPage = new CartPage(driver);
        driver = cartPage.estimate(country, state, zip);

        // Step 7. Verify Shipping cost generated
        WebElement shipping = cartPage.getShipping();

        ScreenshotTaker.takeScreenshot(driver, "TestCase06/Test01.png");
        assertNotNull(shipping);

        // Step 8. Select Shipping Cost, Update Total
        float expectedShip = cartPage.getPrice("shipping fr");

        WebElement comboFR = driver.findElement(By.id("s_method_flatrate_flatrate"));
        comboFR.click();

        WebElement updateTotal = driver.findElement(By.xpath("//button[@title='Update Total']"));
        updateTotal.click();

        float actualShip = cartPage.getActualShippingPrice();

        // Step 9. Verify shipping cost is added to total
        ScreenshotTaker.takeScreenshot(driver, "TestCase06/Test02.png");
        assertEquals(expectedShip, actualShip);

        // Step 10. Click "Proceed to Checkout"
        driver = cartPage.goTo("checkout");

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
