package assignment;

import driver.driverFactory;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pom.*;
import screenshot.ScreenshotTaker;

import static org.junit.jupiter.api.Assertions.*;

public class TestCase05 {
    @Test
    public void TestCase05() {
        // (Note) Change email at EACH runtime
        String firstname = "A", middlename = "B", lastname = "C", email = "toto@ee.com",
        password = "000000";

        // Step 1. Go to http://live.techpanda.org/
        WebDriver driver = driverFactory.getChromeDriver();
        driver.get("http://live.techpanda.org/");

        // Step 2. Click on my account link
        WebElement accountlink = driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account'][normalize-space()='My Account']"));
        accountlink.click(); // Webpage is now http://live.techpanda.org/index.php/customer/account/login/

        // Step 3. Click Create an Account link
        WebElement createButton = driver.findElement(By.xpath("//a[@title='Create an Account']"));
        createButton.click();

        // Step 4. Fill New User information excluding the registered Email ID, then click Register
        Register register = new Register(driver);
        driver = register.signIn(firstname, middlename, lastname, email, password, password);

        String newURL = driver.getCurrentUrl();

        // Step 5. Verify Registration is done. Expected account registration done.
        ScreenshotTaker.takeScreenshot(driver, "TestCase05/Test01.png");
        assertEquals("http://live.techpanda.org/index.php/customer/account/index/",newURL);

        // Step 6. Go to TV menu
        WebElement MOBILElink = driver.findElement(By.className("last"));
        MOBILElink.click(); // Webpage is now http://live.techpanda.org/index.php/tv.html

        // Step 7. Add product in your wish list - use product - LG LCD
        WebElement lg = driver.findElement(By.xpath("//div[h2/a/@title='LG LCD']//div[@class='actions']//ul[@class='add-to-links']//li[1]//a[1]"));
        lg.click();

        // Step 8. Click SHARE WISHLIST
        WebElement shareWish = driver.findElement(By.xpath("//button[@title='Share Wishlist']"));
        shareWish.click();

        // Step 9. In next page enter Email and a message and click SHARE WISHLIST
        WishlistShare wishlistShare = new WishlistShare(driver);
        driver = wishlistShare.wishlist("kk@gmail.com,cc@gmail.com", "Whoops...");
        WebElement notification = driver.findElement(By.xpath("//li[@class='success-msg']//ul[1]//li[1]//span"));
        String successMessage = notification.getText();

        // Step 10. Check wishlist is shared. Expected wishlist shared successfully
        ScreenshotTaker.takeScreenshot(driver, "TestCase05/Test02.png");
        assertEquals("Your Wishlist has been shared.", successMessage);

        // Finally: Close the driver
        driver.close();
    }
}
