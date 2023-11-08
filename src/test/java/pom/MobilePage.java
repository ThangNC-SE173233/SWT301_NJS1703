package pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;

public class MobilePage {
    protected WebDriver driver;
    private final By bySort = By.xpath("//select[@title='Sort By']");
    private final By byAllProducts = By.className("product-name"); // Use this with an Array
    private final By bySony = By.xpath("//div[h2/a/@title='Sony Xperia']");
    private final By bySamsung = By.xpath("//div[h2/a/@title='Samsung Galaxy']");
    private final By byIPhone = By.xpath("//div[h2/a/@title='IPhone']");
    private final By bySubName = By.className("product-name");
    private final By bySubCompare = By.className("link-compare");
    private final By bySubWishlist = By.className("link-wishlist");
    private final By byCompare = By.xpath("//button[@title='Compare']");

    public MobilePage(WebDriver driver) {
        this.driver = driver;
    }

    public String getProductName(String of) {
        WebElement product = null;
        String name = "";
        switch (of.toUpperCase()) {
            case "SONY":
                product = driver.findElement(bySony);
                break;
            case "IPHONE":
                product = driver.findElement(byIPhone);
                break;
            case "SAMSUNG":
                product = driver.findElement(bySamsung);
                break;
        }
        if (product != null) {
            name = product.findElement(bySubName).getText();
        }
        return name;
    }

    public ArrayList<String> getProductNames(boolean sort_by_name) {
        ArrayList<String> names = new ArrayList<>();
        if (sort_by_name) {
            WebElement dropDown = driver.findElement(bySort);
            Select select = new Select(dropDown);
            select.selectByVisibleText("Name");
        }
        List<WebElement> elements = driver.findElements(byAllProducts);

        for (WebElement e : elements) {
            WebElement subE = e.findElement(By.xpath("./child::*"));
            names.add(subE.getText());
        }
        return names;
    }

    public String getPrice(String of) {
        WebElement product = null;
        String price = "";
        switch (of.toUpperCase()) {
            case "SONY":
                product = driver.findElement(bySony);
                break;
            case "IPHONE":
                product = driver.findElement(byIPhone);
                break;
            case "SAMSUNG":
                product = driver.findElement(bySamsung);
                break;
        }
        if (product != null) {
            price = product.findElement(By.className("price")).getText();
        }
        return price;
    }

    public void addToCompare(String product) {
        WebElement element = null;
        switch (product.toUpperCase()) {
            case "SONY":
                element = driver.findElement(bySony);
                break;
            case "IPHONE":
                element = driver.findElement(byIPhone);
                break;
            case "SAMSUNG":
                element = driver.findElement(bySamsung);
                break;
        }
        if (element != null) {
            element.findElement(bySubCompare).click();
        }
    }

    public WebDriver goTo(String product) {
        switch (product.toUpperCase()) {
            case "SONY":
                driver.findElement(bySony).click();
                break;
            case "IPHONE":
                driver.findElement(byIPhone).click();
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

    public WebDriver getDriver() {
        return driver;
    }
}
