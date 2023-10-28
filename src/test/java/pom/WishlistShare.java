package pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class WishlistShare {
    protected WebDriver driver;
    private By byEmails = By.id("email_address");
    private By byMessage = By.id("message");
    private By byShare = By.xpath("//button[@title='Share Wishlist']");

    public WishlistShare(WebDriver driver) {
        this.driver = driver;
    }

    public WebDriver wishlist(String emails, String message) {
        // Fill in the textbox value
        driver.findElement(byEmails).sendKeys(emails);
        driver.findElement(byMessage).sendKeys(message);

        // Click Share Wishlist, and return the driver
        driver.findElement(byShare).click();
        return driver;
    }
}
