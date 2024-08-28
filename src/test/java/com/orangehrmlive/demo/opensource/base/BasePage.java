package com.orangehrmlive.demo.opensource.base;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.Random;

public class BasePage<T extends BasePage<T>> {
    //Driver
    protected WebDriver driver;

    //Constructor
    public BasePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public T load() {
        return (T) this;
    }

    public String getCurrentPageUrl() {
        return driver.getCurrentUrl();
    }

    public WebDriverWait explicitWait() {
        return new WebDriverWait(this.driver, Duration.ofSeconds(15));

    }

    public Actions actions() {
        return new Actions(this.driver);

    }

    public void selectFromTheDropDownWithKeys() {
        actions().sendKeys(Keys.ARROW_DOWN).perform();
        actions().sendKeys(Keys.ENTER).perform();
    }

    public void selectFromTheDropDown(WebElement dropdown, String selection) {
        dropdown.click();
        selectSpecificOptionFromTheDropDown(selection);
    }

    public void selectSpecificOptionFromTheDropDown(String selection) {
        WebElement option = explicitWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@role='listbox']//span[contains(text(), '" + selection + "')][1]")));
        option.click();
    }

}