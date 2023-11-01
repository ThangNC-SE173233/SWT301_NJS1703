package pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CheckOut {
    protected WebDriver driver;

    private By byFirstName = By.id("billing:firstname");
    private By byMiddleName = By.id("billing:middlename");
    private By byLastName = By.id("billing:lastname");
    private By byCompany = By.id("billing:company");
    private By byAddress = By.id("billing:street1");
    private By byAddressOptional = By.id("billing:street2");
    private By byCity = By.id("billing:city");
    private By byRegion = By.id("billing:region_id"); //Dropdown menu
    private By byZIP = By.id("billing:postcode");
    private By byCountry = By.id("billing:country_id");
    private By byTelephone = By.id("billing:telephone");
    private By byFax = By.id("billing:fax");
    private By byContinue = By.xpath("//button[@title='Continue']");
    public CheckOut(WebDriver driver) {
        this.driver = driver;
    }

    public WebDriver enterExtraField(String type, String value) {
        switch (type.toUpperCase()) {
            case "FIRST NAME":
                driver.findElement(byFirstName).sendKeys(value);
                break;
            case "MIDDLE NAME":
                driver.findElement(byMiddleName).sendKeys(value);
                break;
            case "LAST NAME":
                driver.findElement(byLastName).sendKeys(value);
                break;
            case "COMPANY":
                driver.findElement(byCompany).sendKeys(value);
            case "STREET ADDRESS 2":
                driver.findElement(byAddressOptional).sendKeys(value);
            case "FAX":
                driver.findElement(byFax).sendKeys(value);
        }
        return driver;
    }

    public WebDriver enterRequiredField(String address, String city, String state,
                                    String zip, String country, String telephone) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        Select addressBook = new Select(driver.findElement(By.id("billing-address-select")));
        addressBook.selectByVisibleText("New Address");

        driver.findElement(byAddress).clear();
        driver.findElement(byAddress).sendKeys(address);

        driver.findElement(byCity).clear();
        driver.findElement(byCity).sendKeys(city);

        Select select = new Select(driver.findElement(byRegion));
        select.selectByVisibleText(state);

        driver.findElement(byZIP).sendKeys(zip);

        driver.findElement(byCountry).sendKeys(country);

        driver.findElement(byTelephone).sendKeys(telephone);

        driver.findElement(byContinue).click();

        byContinue = By.xpath("//button[@onclick='shippingMethod.save()']");

        WebElement btnContinue = wait.until(ExpectedConditions.elementToBeClickable(byContinue));
        btnContinue.click();

        byContinue = By.xpath("//button[@onclick='payment.save()']");
        btnContinue = wait.until(ExpectedConditions.elementToBeClickable(byContinue));

        WebElement radio = driver.findElement(By.id("p_method_checkmo"));
        radio.click();
        btnContinue.click();

        byContinue = By.xpath("//button[@title='Place Order']");
        btnContinue = wait.until(ExpectedConditions.elementToBeClickable(byContinue));
        btnContinue.click();
        return driver;
    }
}
