package pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class TVPage {
    protected WebDriver driver;
    private final By bySort = By.xpath("//select[@title='Sort By']");
    private final By byAllProducts = By.className("product-name"); // Use this with an Array
    private final By byLG = By.xpath("//div[h2/a/@title='LG LCD']");
    private final By bySamsung = By.xpath("//div[h2/a/@title='Samsung LCD']");
    private final By bySubName = By.className("product-name");
    private final By bySubCompare = By.className("link-compare");
    private final By bySubWishlist = By.className("link-wishlist");
    private final By byCompare = By.xpath("//button[@title='Compare']");

    public TVPage(WebDriver driver) {
        this.driver = driver;
    }

    public WebDriver getDriver() {
        return driver;
    }

    public WebDriver goTo(String product) {
        switch (product.toUpperCase()) {
            case "LG":
                driver.findElement(byLG).click();
                break;
            case "SAMSUNG":
                driver.findElement(bySamsung).click();
                break;
            case "COMPARE":
                driver.findElement(byCompare).click();
                break;
        }
        return getDriver();
    }

    public WebDriver addToWishlist(String product) {
        WebElement element = null;
        switch (product.toUpperCase()) {
            case "LG":
                element = driver.findElement(byLG);
                break;
            case "SAMSUNG":
                element = driver.findElement(bySamsung);
                break;
        }
        if (element != null) {
            element.findElement(bySubWishlist).click();
        }
        return getDriver();
    }
}
