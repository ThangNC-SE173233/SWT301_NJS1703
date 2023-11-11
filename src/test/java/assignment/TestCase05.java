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
        String firstname = "A", middlename = "B", lastname = "C", email = "somethinglikethat@email.com",
        password = "000000";

        // Step 1. Go to http://live.techpanda.org/
        WebDriver driver = driverFactory.getChromeDriver();
        driver.get("http://live.techpanda.org/");

        // Step 2. Click on my account link
        HomePage home = new HomePage(driver);
        home.goTo("my account"); // Webpage is now http://live.techpanda.org/index.php/customer/account/login/

        // Step 3. Click Create an Account link
        Login login = new Login(driver);
        driver = login.goTo("create account");

        // Step 4. Fill New User information excluding the registered Email ID, then click Register
        Register register = new Register(driver);
        driver = register.signIn(firstname, middlename, lastname, email, password, password);

        Dashboard dash = new Dashboard(driver);

        // Step 5. Verify Registration is done. Expected account registration done.
        ScreenshotTaker.takeScreenshot(driver, "TestCase05/Test01.png");
        assertEquals("http://live.techpanda.org/index.php/customer/account/index/",dash.getURL());

        // Step 6. Go to TV menu
        driver = dash.goTo("tv"); // Webpage is now http://live.techpanda.org/index.php/tv.html

        // Step 7. Add product in your wish list - use product - LG LCD
        TVPage tv = new TVPage(driver);
        driver = tv.addToWishlist("lg");

        // Step 8. Click SHARE WISHLIST
        driver.findElement(By.name("save_and_share")).click();

        // Step 9. In next page enter Email and a message and click SHARE WISHLIST
        WishlistShare wishlistShare = new WishlistShare(driver);
        driver = wishlistShare.wishlist("kk@gmail.com,cc@gmail.com", "Whoops...");

        // Step 10. Check wishlist is shared. Expected wishlist shared successfully
        ScreenshotTaker.takeScreenshot(driver, "TestCase05/Test02.png");
        assertEquals("Your Wishlist has been shared.", wishlistShare.getSuccessMessage());

        // Finally: Close the driver
        driver.close();
    }
}
