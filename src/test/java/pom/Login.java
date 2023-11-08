package pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Login {
    protected WebDriver driver;
    private final By byEmail = By.id("email");
    private final By byPassword = By.id("pass");
    private final By byLogin = By.id("send2");
    private final By byCreateAccount = By.xpath("//a[@title='Create an Account']");

    public Login(WebDriver driver) {
        this.driver = driver;
    }

    public WebDriver getDriver() {
        return driver;
    }

    public WebDriver signIn(String email, String password) {
        // Fill in the textbox value
        driver.findElement(byEmail).sendKeys(email);
        driver.findElement(byPassword).sendKeys(password);

        // Click Login, and return the driver
        return goTo("LOGIN");
    }

    public WebDriver goTo(String page) {
        switch (page.toUpperCase()) {
            case "CREATE ACCOUNT":
                driver.findElement(byCreateAccount).click();
                break;
            case "LOGIN":
                driver.findElement(byLogin).click();
                break;
        }
        return getDriver();
    }
}
