package pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class ProductComparison {
    protected WebDriver driver;
    private final By byProductName = By.className("product-name");

    public ProductComparison(WebDriver driver) {
        this.driver = driver;
    }

    public WebDriver getDriver() {
        return driver;
    }

    public ArrayList<String> getProductNames() {
        ArrayList<String> names = new ArrayList<>();
        List<WebElement> elements = driver.findElements(byProductName);
        for (WebElement e : elements) {
            names.add(e.getText());
        }
        return names;
    }
}
