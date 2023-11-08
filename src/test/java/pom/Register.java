package pom;

import com.github.dockerjava.api.model.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Register {
    protected WebDriver driver;
    private final By byFirstName = By.id("firstname");
    private final By byMiddleName = By.id("middlename");
    private final By byLastName = By.id("lastname");
    private final By byEmailAddress = By.id("email_address");
    private final By byPassword = By.id("password");
    private final By byConfirmation = By.id("confirmation");
    private final By byRegister = By.xpath("//button[@title='Register']");

    public Register(WebDriver driver) {
        this.driver = driver;
    }

    public WebDriver signIn(String firstname, String middlename, String lastname,
                            String email, String password, String confirm)
    {
        // Fill in the textbox value
        driver.findElement(byFirstName).sendKeys(firstname);
        driver.findElement(byMiddleName).sendKeys(middlename);
        driver.findElement(byLastName).sendKeys(lastname);
        driver.findElement(byEmailAddress).sendKeys(email);
        driver.findElement(byPassword).sendKeys(password);
        driver.findElement(byConfirmation).sendKeys(confirm);

        // Click submit, and return the driver
        driver.findElement(byRegister).click();
        return driver;
    }
}
