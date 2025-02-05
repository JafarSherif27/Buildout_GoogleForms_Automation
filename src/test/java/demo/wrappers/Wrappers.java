package demo.wrappers;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

@SuppressWarnings({ "unused" })
public class Wrappers {
    /*
     * Write your selenium wrappers here
     */
    public WebDriver driver;
    Actions actions;
    WebDriverWait wait;


    public Wrappers(WebDriver driver) {
        this.driver = driver;
        this.actions = new Actions(driver);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(25));
    }


    public void logStatus(String description, String testID) {
        String timeStamp = String.valueOf(java.time.LocalDateTime.now());
        System.out.println(String.format("%s | %s | %s", timeStamp, description, testID));

    }


    public void logStatus(String description, boolean status) {
        String timeStamp = String.valueOf(java.time.LocalDateTime.now());
        System.out.println(String.format("%s | %s | %s", timeStamp, description, (status ? "Passed" : "Failed")));
    }


    public void sendKeys(By locator, String text) {
        try {
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            element.clear();
            element.sendKeys(text);

        } catch (Exception e) {
            System.out.println("Failed to enter text in element: " + locator);
            e.printStackTrace();
        }
    }


    public void clickOnElement(By locator) {
        try {
            WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
            element.click();

        } catch (Exception e) {
            System.out.println("Failed to click on element: " + locator);
            e.printStackTrace();
        }
    }


    public void navigateToUrl(String url) {
        driver.get(url);
    }


    public String getText(By locator) {
        try {
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            return element.getText();

        } catch (Exception e) {
            System.out.println("Failed to get text from element: " + locator);
            e.printStackTrace();
            return null;
        }

    }


    public String getEpoch() {
        long epoch = System.currentTimeMillis() / 1000;
        return String.valueOf(epoch);

    }

}
