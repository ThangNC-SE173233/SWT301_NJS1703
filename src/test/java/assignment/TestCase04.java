package assignment;

import driver.driverFactory;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class TestCase04 {
    @Test
    public void TestCase04() {
        // Step 1. Go to http://live.techpanda.org/
        WebDriver driver = driverFactory.getChromeDriver();
        driver.get("http://live.techpanda.org/");

        String mainWindow = driver.getWindowHandle();

        // Step 2. Click on -> MOBILE -> menu
        WebElement MOBILElink = driver.findElement(By.className("first"));
        MOBILElink.click(); // Webpage is now http://live.techpanda.org/index.php/mobile.html

        // Step 3. In mobile products list , click on Add To Compare for 2 mobiles (Sony Xperia & Iphone)
        ArrayList<String> test_names = new ArrayList<>();
        WebElement sony = driver.findElement(By.xpath("//a[@title='Sony Xperia']"));
        test_names.add(sony.getText());

        sony = driver.findElement(By.xpath("//div[h2/a/@title='Sony Xperia']//div[@class='actions']//ul[@class='add-to-links']//li[2]//a[1]"));
        sony.click();

        WebElement iphone = driver.findElement(By.xpath("//a[@title='IPhone'][normalize-space()='IPhone']"));
        test_names.add(iphone.getText());

        iphone = driver.findElement(By.xpath("//div[h2/a/@title='IPhone']//div[@class='actions']//ul[@class='add-to-links']//li[2]//a[1]"));
        iphone.click();

        // Step 4. Click on COMPARE button. A popup window opens
        WebElement compare = driver.findElement(By.xpath("//button[@title='Compare']"));
        compare.click();

        // Step 5. Verify the pop-up window and check that the products are reflected in it
        for (String handle: driver.getWindowHandles()) {
            driver.switchTo().window(handle);
        }

        ArrayList<String> actual_names = new ArrayList<>();
        WebElement new_sony = driver.findElement(By.xpath("//a[normalize-space()='Sony Xperia']"));
        actual_names.add(new_sony.getText());

        WebElement new_iphone = driver.findElement(By.xpath("//a[normalize-space()='IPhone']"));
        actual_names.add(new_iphone.getText());

        assertEquals(test_names, actual_names);

        // Finally: Close all popups and the driver
        driver.close();
        driver.switchTo().window(mainWindow);
        driver.close();
    }
}
