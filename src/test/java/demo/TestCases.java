package demo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
// import io.github.bonigarcia.wdm.WebDriverManager;
import demo.wrappers.Wrappers;
import net.bytebuddy.asm.Advice.Local;

@SuppressWarnings({"unused"})
public class TestCases {
    ChromeDriver driver;
    Wrappers wrappers;
    String expectedMessage = "Thanks for your response, Automation Wizard!";
    String actualMessage = "";

    /*
     * TODO: Write your tests here with testng @Test annotation. 
     * Follow `testCase01` `testCase02`... format or what is provided in instructions
     */

    @Test
    public void testCase01(){
        wrappers.logStatus("Start Test Case","testCase01");

        wrappers.navigateToUrl("https://docs.google.com/forms/d/e/1FAIpQLSep9LTMntH5YqIXa5nkiPKSs283kdwitBBhXWyZdAS-e4CxBQ/viewform");

        wrappers.sendKeys(By.xpath("(//input[@class='whsOnd zHQkBf'])[1]"), "Crio Learner");

        
        System.out.println("epoch: "+wrappers.getEpoch());
        wrappers.sendKeys(By.tagName("textarea"),String.format("%s %s","I want to be the best QA Engineer!",wrappers.getEpoch()));

        wrappers.clickOnElement(By.xpath("(//div[@class='bzfPab wFGF8'])[1]"));

        wrappers.clickOnElement(By.xpath("(//div[@class='bzfPab wFGF8'])[5]"));
        wrappers.clickOnElement(By.xpath("(//div[@class='bzfPab wFGF8'])[6]"));
        wrappers.clickOnElement(By.xpath("(//div[@class='bzfPab wFGF8'])[8]"));

        wrappers.clickOnElement(By.className("DEh1R"));
        wrappers.clickOnElement(By.xpath("//div[@data-value='Mr' and @role='option']"));


        LocalDate currDate = LocalDate.now();
        LocalDate dateSevenDaysBefore = currDate.minusDays(7);

        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        System.out.println("Date7daysAgo: "+dateSevenDaysBefore.format(dateFormat));
        wrappers.sendKeys(By.xpath("//input[@type='date']"),dateSevenDaysBefore.format(dateFormat));


        LocalTime currentTime = LocalTime.now();
        DateTimeFormatter timeFormatH = DateTimeFormatter.ofPattern("HH");

        System.out.println("Hour: "+currentTime.format(timeFormatH));
        wrappers.sendKeys(By.xpath("(//input[@class='whsOnd zHQkBf'])[3]"),currentTime.format(timeFormatH));

        DateTimeFormatter timeFormatM = DateTimeFormatter.ofPattern("mm");

        System.out.println("Minutes: "+currentTime.format(timeFormatM));
        wrappers.sendKeys(By.xpath("(//input[@class='whsOnd zHQkBf'])[4]"),currentTime.format(timeFormatM));

        wrappers.clickOnElement(By.xpath("(//span[@class='l4V7wb Fxmcue'])[1]"));

        this.actualMessage = wrappers.getText(By.xpath("//div[@class='vHW8K']"));

        System.out.println("The success message: "+actualMessage);

        Assert.assertEquals(actualMessage, expectedMessage,"Success message mismatched");

        wrappers.logStatus("End Test Case","testCase01");
    }



     
    /*
     * Do not change the provided methods unless necessary, they will help in automation and assessment
     */
    @BeforeTest
    public void startBrowser()
    {
        System.setProperty("java.util.logging.config.file", "logging.properties");

        // NOT NEEDED FOR SELENIUM MANAGER
        // WebDriverManager.chromedriver().timeout(30).setup();

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

    @AfterTest
    public void endTest()
    {
        driver.close();
        driver.quit();

    }
}