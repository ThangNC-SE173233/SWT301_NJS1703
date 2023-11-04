package pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ShoppingPage {
    protected WebDriver driver;
    By byQuantity = By.xpath("//input[@title='Qty']");
    By byUpdate = By.xpath("//button[@title='Update']");
    By byTotal = By.xpath("//*[@id='shopping-cart-totals-table']//tfoot//tr//td[2]//strong//span[@class='price']");

    public ShoppingPage(WebDriver driver) {
        this.driver = driver;
    }

    public WebDriver getDriver() {
        return driver;
    }

    public String getTotalPrice(String quantity) {
        WebElement qty = driver.findElement(byQuantity);
        qty.clear();
        qty.sendKeys(quantity);

        WebElement update = driver.findElement(byUpdate);
        update.click();

        WebElement total = driver.findElement(byTotal);
        return total.getText();
    }
}
