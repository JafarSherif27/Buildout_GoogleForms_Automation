package demo.locators;

import org.openqa.selenium.By;

public class Locators {

    public static final By NAME_FIELD = By.xpath("//div[text()='Your answer']/preceding-sibling::input[@type='text']");

    public static final By TEXT_FIELD = By.tagName("textarea");

    public static final By DATE_FIELD = By.xpath("//input[@type='date']");

    public static final By HOUR_FIELD = By.xpath("//input[@aria-label='Hour']");

    public static final By MINUTE_FIELD = By.xpath("//input[@aria-label='Minute']");

    public static final By CHECKBOX_ELEMENTS = By.xpath("//label[contains(@class,'Yri8Nb')]");

    public static final By DROPDOWN_ELEMENT = By.xpath("//div[contains(@class, 'ry3kXd')]");

    public static final By SUBMIT_BUTTON = By.xpath("//span[text()='Submit']/parent::node()");

    public static final By SUCCESS_MESSAGE = By.xpath("//div[@class='vHW8K']");

    public static By experienceRadioBtn(String optionToSelect) {
        return By.xpath("//span[text()='" + optionToSelect + "']/ancestor::div[contains(@class,'nWQGrd')]");

    }

    public static By honorficOption(String optionToSelect) {
        return By.xpath("//span[text()='" + optionToSelect + "']/parent::div[@role='option']");

    }

}