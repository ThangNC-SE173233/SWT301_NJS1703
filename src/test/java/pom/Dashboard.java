package pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Dashboard {
    protected WebDriver driver;
    private final By byMobile = By.className("nav-1");
    private final By byTV = By.className("nav-2");
    private final By byOrders = By.xpath("//a[@href='http://live.techpanda.org/index.php/sales/order/history/']");

    public Dashboard(WebDriver driver) {
        this.driver = driver;
    }

    public WebDriver getDriver() {
        return driver;
    }

    public WebDriver goTo(String section) {
        switch (section.toUpperCase()) {
            case "MOBILE":
                driver.findElement(byMobile).click();
                break;
            case "TV":
                driver.findElement(byTV).click();
                break;
            case "MY ORDERS":
                driver.findElement(byOrders).click();
                break;
        }
        return getDriver();
    }

    public String getURL() {
        return driver.getCurrentUrl();
    }
}
