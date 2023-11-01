package pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Login {
    protected WebDriver driver;
    private By byEmail = By.id("email");
    private By byPassword = By.id("pass");
    private By byLogin = By.id("send2");
    public Login(WebDriver driver) {
        this.driver = driver;
    }

    public WebDriver signIn(String email, String password) {
        // Fill in the textbox value
        driver.findElement(byEmail).sendKeys(email);
        driver.findElement(byPassword).sendKeys(password);

        // Click Login, and return the driver
        driver.findElement(byLogin).click();
        return driver;
    }
}
