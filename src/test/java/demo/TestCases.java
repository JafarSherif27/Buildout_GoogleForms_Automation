package demo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
// import io.github.bonigarcia.wdm.WebDriverManager;
import demo.wrappers.Wrappers;
import demo.locators.Locators;
import net.bytebuddy.asm.Advice.Local;


@SuppressWarnings({"unused"})
public class TestCases {
    ChromeDriver driver;
    Wrappers wrappers;
    final String expectedMessage = "Thanks for your response, Automation Wizard!";
    String actualMessage = "";


    //Test (Automation script)
    @Test(invocationCount = 10)
    public void testCase01(){
        wrappers.logStatus("Start Test Case","testCase01");

        //Navigate to google form
        wrappers.navigateToUrl(Wrappers.HOMEPAGE_URL);


        //Enter "Crio Learner" in NameField -Avoid using indexing -cause prone to break
        wrappers.sendKeys(Locators.NAME_FIELD, "Crio Learner"); 


        //Enter a text with epoch 
        wrappers.sendKeys(Locators.TEXT_FIELD,String.format("%s %s","I want to be the best QA Engineer!",wrappers.getEpoch()));


        //Select the experience radio button -- do not use hard coded xpath 
        wrappers.selectExperience("0 - 2");


        //Select checkbox of known tools -- do not use hard coded xpath 
        wrappers.selectLearned("Java");
        wrappers.selectLearned("Selenium");
        wrappers.selectLearned("TestNG");
        

        //Select dropdown and select honorfic
        wrappers.selectHonorfic("Mr");


        //Enter the date of 7 days ago in dd-MM-yyyy 
        LocalDate currentDate = LocalDate.now();
        LocalDate dateSevenDaysBefore = currentDate.minusDays(7);

        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        wrappers.sendKeys(Locators.DATE_FIELD,dateSevenDaysBefore.format(dateFormat));


        //Enter time 07:30 in HH:mm 
        wrappers.sendKeys(Locators.HOUR_FIELD,"07");
        wrappers.sendKeys(Locators.MINUTE_FIELD,"30"); 


        //Submit the form
        wrappers.clickOnElement(Locators.SUBMIT_BUTTON);


        //Assert success message 
        this.actualMessage = wrappers.getText(Locators.SUCCESS_MESSAGE);
        Assert.assertEquals(actualMessage, expectedMessage,"Success message mismatched");
        
        wrappers.logStatus("End Test Case","testCase01");
    }



     
   //Set-up 
    @BeforeTest
    public void startBrowser(){
        System.setProperty("java.util.logging.config.file", "logging.properties");

        // NOT NEEDED FOR SELENIUM MANAGER
        // WebDriverManager.chromedriver().timeout(Max_Wait_Time).setup();

        ChromeOptions options = new ChromeOptions();
        LoggingPreferences logs = new LoggingPreferences();

        logs.enable(LogType.BROWSER, Level.ALL);
        logs.enable(LogType.DRIVER, Level.ALL);
        options.setCapability("goog:loggingPrefs", logs);
        options.addArguments("--remote-allow-origins=*");

        System.setProperty(ChromeDriverService.CHROME_DRIVER_LOG_PROPERTY, "build/chromedriver.log"); 
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();

        this.wrappers = new Wrappers(driver);
    }


    //Teardown
    @AfterTest
    public void endTest(){
        driver.close();
        driver.quit();
    }

}
