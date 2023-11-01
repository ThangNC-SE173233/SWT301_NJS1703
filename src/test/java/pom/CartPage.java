package pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class CartPage {
    protected WebDriver driver;

    private By byCountry = By.id("country");
    private By byRegion = By.id("region_id");
    private By byZIP = By.id("postcode");
    private By byEstimate = By.xpath("//button[@title='Estimate']");

    public CartPage(WebDriver driver) {
        this.driver = driver;
    }

    public WebDriver estimate(String country, String state, String zip) {
        // Fill in all the information
        Select select1 = new Select(driver.findElement(byCountry));
        select1.selectByVisibleText(country);

        Select select2 = new Select(driver.findElement(byRegion));
        select2.selectByVisibleText(state);

        driver.findElement(byZIP).sendKeys(zip);

        // Click Estimate, then return the driver
        driver.findElement(byEstimate).click();
        return driver;
    }
}
