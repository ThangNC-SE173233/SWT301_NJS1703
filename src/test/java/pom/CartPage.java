package pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class CartPage {
    protected WebDriver driver;

    private final By byTitle = By.xpath("//div[@class='page-title']//h1");
    private final By byQuantity = By.xpath("//input[@title='Qty']");
    private final By byUpdate = By.xpath("//button[@title='Update']");
    private final By byUpdateTotal = By.xpath("//button[@title='Update Total']");
    private final By byCheckout = By.className("btn-checkout");
    private final By byCountry = By.id("country");
    private final By byRegion = By.id("region_id");
    private final By byZIP = By.id("postcode");
    private final By byEstimate = By.xpath("//button[@title='Estimate']");
    private final By byCouponCode = By.id("coupon_code");
    private final By byApplyCC = By.xpath("//button[@title='Apply']");
    private final By byErrorMessage = By.className("error");
    private final By byCCMessage = By.xpath("//li[@class='success-msg']//ul//li//span");
    private final By bySubTotal = By.xpath("//tbody//tr[1]//td[2]//span[@class='price']");
    private final By byDiscount = By.xpath("//tbody//tr[2]//td[2]//span[@class='price']");
    private final By byShippingFR = By.xpath("//label[@for='s_method_flatrate_flatrate']//span[@class='price']");
    private final By byShipping = By.xpath("//tbody//tr[2]//td[2]//span[@class='price']");
    private final By byGrandTotal = By.xpath("//tfoot//td[2]//strong//span[@class='price']");
    private final By byEmptyCart = By.id("empty_cart_button");
    private final By byComboFR = By.id("s_method_flatrate_flatrate");

    public CartPage(WebDriver driver) {
        this.driver = driver;
    }

    public WebDriver getDriver() {
        return driver;
    }

    public String getTitle() {
        WebElement title = driver.findElement(byTitle);
        return title.getText();
    }

    public WebDriver estimate(String country, String state, String zip) {
        // Fill in all the information
        Select select1 = new Select(driver.findElement(byCountry));
        select1.selectByVisibleText(country);

        Select select2 = new Select(driver.findElement(byRegion));
        select2.selectByVisibleText(state);

        driver.findElement(byZIP).sendKeys(zip);

        // Click Estimate, then return the driver
        return goTo("ESTIMATE");
    }

    public float getPrice(String type) {
        WebElement element = null;
        float value = 0;
        switch (type.toUpperCase()) {
            case "SHIPPING FR":
                element = driver.findElement(byShippingFR);
                break;
            case "SHIPPING":
                element = driver.findElement(byShipping);
                break;
            case "SUB":
                element = driver.findElement(bySubTotal);
                break;
            case "DISCOUNT":
                element = driver.findElement(byDiscount);
                break;
            case "GRAND":
                element = driver.findElement(byGrandTotal);
        }
        if (element != null) {
            value = Float.parseFloat(element.getText().replaceAll("[^\\d.]+", ""));
        }
        return value;
    }

    public void click(String on) {
        switch (on.toUpperCase()) {
            case "FLAT RATE":
                driver.findElement(byComboFR).click();
                break;
            case "UPDATE":
                driver.findElement(byUpdate).click();
                break;
            case "UPDATE TOTAL":
                driver.findElement(byUpdateTotal).click();
        }
    }

    public WebDriver goTo(String page) {
        switch (page.toUpperCase()) {
            case "EMPTY CART":
                driver.findElement(byEmptyCart).click();
                break;
            case "ESTIMATE":
                driver.findElement(byEstimate).click();
                break;
            case "CHECKOUT":
                driver.findElement(byCheckout).click();
                break;
        }
        return getDriver();
    }

    public String getErrorMessage(String quantity) {
        WebElement qty = driver.findElement(byQuantity);
        qty.clear();
        qty.sendKeys(quantity);

        click("UPDATE");

        WebElement message = driver.findElement(byErrorMessage);
        return message.getText();
    }

    public String applyCoupon(String code) {
        // Fill in all the information
        driver.findElement(byCouponCode).sendKeys(code);
        driver.findElement(byApplyCC).click();

        WebElement message = driver.findElement(byCCMessage);
        return message.getText();
    }

    public WebElement getShipping() {
        return driver.findElement(byShippingFR);
    }

    public float getTotalPrice(String quantity) {
        WebElement qty = driver.findElement(byQuantity);
        qty.clear();
        qty.sendKeys(quantity);

        click("UPDATE");

        return getPrice("GRAND");
    }

    public float getActualShippingPrice() {
        click("FLAT_RATE");
        click("UPDATE TOTAL");
        return getPrice("SHIPPING");
    }
}
