package pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class WishlistShare {
    protected WebDriver driver;
    private final By byEmails = By.id("email_address");
    private final By byMessage = By.id("message");
    private final By byShare = By.xpath("//button[@title='Share Wishlist']");
    private final By bySuccess = By.xpath("//li[@class='success-msg']//ul[1]//li[1]//span");

    public WishlistShare(WebDriver driver) {
        this.driver = driver;
    }

    public WebDriver getDriver() {
        return driver;
    }

    public WebDriver goTo(String on) {
        switch (on.toUpperCase()) {
            case "SHARE WISHLIST":
                driver.findElement(byShare).click();
                break;
        }
        return getDriver();
    }

    public WebDriver wishlist(String emails, String message) {
        // Fill in the textbox value
        driver.findElement(byEmails).sendKeys(emails);
        driver.findElement(byMessage).sendKeys(message);

        // Click Share Wishlist, and return the driver
        return goTo("SHARE WISHLIST");
    }

    public String getSuccessMessage() {
        return driver.findElement(bySuccess).getText();
    }
}
