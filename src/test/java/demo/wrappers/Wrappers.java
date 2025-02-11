package demo.wrappers;

import java.util.List;
import java.time.Instant;
import java.time.Duration;
import org.openqa.selenium.By;
import demo.locators.Locators;
import java.time.LocalDateTime;
import org.openqa.selenium.Keys;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.time.format.DateTimeFormatter;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;


@SuppressWarnings({ "unused" })
public class Wrappers {
    public WebDriver driver;
    Actions actions;
    WebDriverWait wait;
    final int maxWaitTime = 20;
    public static final String HOMEPAGE_URL = "https://docs.google.com/forms/d/e/1FAIpQLSep9LTMntH5YqIXa5nkiPKSs283kdwitBBhXWyZdAS-e4CxBQ/viewform";
   

    // Constructor
    public Wrappers(WebDriver driver) {
        this.driver = driver;
        this.actions = new Actions(driver);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(maxWaitTime));
    }


    // To print Start and End test case logs
    public void logStatus(String description, String testID) {
        String timeStamp = String.valueOf(java.time.LocalDateTime.now());
        System.out.println(String.format("%s | %s | %s", timeStamp, description, testID));

    }


    // To print logs with boolean
    public void logStatus(String description, boolean status) {
        String timeStamp = String.valueOf(java.time.LocalDateTime.now());
        System.out.println(String.format("%s | %s | %s", timeStamp, description, (status ? "Passed" : "Failed")));
    }


    // To Enter text in textFields
    public void sendKeys(By locator, String text) {
        try {
            // elementToBeClickable is used to wait until the element is interactable
            WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
            element.clear();
            element.sendKeys(text);

        } catch (Exception e) {
            System.out.println("Failed to enter text in element: " + locator);
            e.printStackTrace();
        }
    }


    // To click on a WebElement
    public void clickOnElement(By locator) {
        try {
            WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
            element.click();

        } catch (Exception e) {
            System.out.println("Failed to click on element: " + locator);
            e.printStackTrace();
        }
    }


    // To navigate to url
    public void navigateToUrl(String url) {
        driver.get(url);
    }


    // To get text of WebElement
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


    // To get epoch in String format
    public String getEpoch() {
        long epoch = System.currentTimeMillis() / 1000;
        return String.valueOf(epoch);

    }


    // Select experience radio button
    public void selectExperience(String optionToSelect) {
        clickOnElement(Locators.experienceRadioBtn(optionToSelect));

    }


    // Select check boxes of learned in Crio.Do 
    public void selectLearned(String optionToSelect) {
        boolean status = false;
        try {
            List<WebElement> elements = wait.until(ExpectedConditions
                    .visibilityOfAllElementsLocatedBy(Locators.CHECKBOX_ELEMENTS));

            for (WebElement element : elements) {
                if (element.getText().contains(optionToSelect) && element.isEnabled()) {
                    element.click();
                    status = true;
                    break;
                }
            }

            //If there is no check box with the text
            if(!status){
                System.out.println("No check box found for "+optionToSelect);
            }

        } catch (Exception e) {
            System.out.println("Failed to select check box "+optionToSelect+": " + e.getMessage());
            
        }
    }


    //Open dropdown and select honorfic
    public void selectHonorfic(String optionToSelect){
        //Open dropdown
        clickOnElement(Locators.DROPDOWN_ELEMENT);

        //Select honorfic
        clickOnElement(Locators.honorficOption(optionToSelect));

    }



}
