package pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Orders {
    protected WebDriver driver;
    private By byOrder = By.id("sales_order_grid_filter_real_order_id");
    private By byFromDate = By.name("created_at[from]");
    private By byToDate = By.name("created_at[to]");
    private By bySearch = By.xpath("//button[@title='Search']");

    public Orders(WebDriver driver) {
        this.driver = driver;
    }

    public WebDriver getDriver() {
        return driver;
    }

    public void searchForOrder(String id, String fromDate, String toDate) {
        if (!id.isEmpty()) {
            driver.findElement(byOrder).sendKeys(id);
        }
        if (!fromDate.isEmpty()) {
            driver.findElement(byFromDate).sendKeys(fromDate);
        }
        if (!toDate.isEmpty()) {
            driver.findElement(byToDate).sendKeys(toDate);
        }
        driver.findElement(bySearch).click();
    }
}
