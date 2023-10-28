package pom;

import com.github.dockerjava.api.model.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Register {
    protected WebDriver driver;
    private By byFirstName = By.id("firstname");
    private By byMiddleName = By.id("middlename");
    private By byLastName = By.id("lastname");
    private By byEmailAddress = By.id("email_address");
    private By byPassword = By.id("password");
    private By byConfirmation = By.id("confirmation");
    private By byRegister = By.xpath("//button[@title='Register']");

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
