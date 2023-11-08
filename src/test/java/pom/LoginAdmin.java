package pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginAdmin {

    protected WebDriver driver;
    private By byEmail = By.id("username");
    private By byPassword = By.id("login");
    private By byLogin = By.xpath("//input[@title='Login']");
    public LoginAdmin(WebDriver driver) {
        this.driver = driver;
    }

    public WebDriver getDriver() {
        return driver;
    }

    public void login(String username, String password) {
        // Fill in all the information
        driver.findElement(byEmail).sendKeys(username);
        driver.findElement(byPassword).sendKeys(password);

        // Click Login
        driver.findElement(byLogin).click();
    }
}
