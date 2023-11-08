package pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage {
    protected WebDriver driver;
    private final By byTitle = By.className("page-title");
    private final By byMobile = By.className("first");
    private final By byTV = By.className("last");
    private final By byMyAccount = By.xpath("//div[@class='footer']//a[@title='My Account']");

    public HomePage(WebDriver driver) {
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
            case "MY ACCOUNT":
                driver.findElement(byMyAccount).click();
                break;
        }
        return getDriver();
    }

    public String getTitle() {
        WebElement title = driver.findElement(byTitle);
        return title.getText();
    }
}
