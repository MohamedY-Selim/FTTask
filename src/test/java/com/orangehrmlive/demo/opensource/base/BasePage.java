package com.orangehrmlive.demo.opensource.base;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage<T extends BasePage<T>> {
    // Driver
    protected WebDriver driver;

    // Constructor
    public BasePage(WebDriver driver) {
        this.driver = driver;
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

    public void selectFromTheDropDown(By dropdownLocator, String selection) {
        driver.findElement(dropdownLocator).click();
        selectSpecificOptionFromTheDropDown(selection);
    }

    public void selectSpecificOptionFromTheDropDown(String selection) {
        By optionLocator = By.xpath("//div[@role='listbox']//span[contains(text(), '" + selection + "')][1]");
        WebElement option = explicitWait().until(ExpectedConditions.visibilityOfElementLocated(optionLocator));
        option.click();
    }
}
