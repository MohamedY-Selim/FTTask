package com.orangehrmlive.demo.opensource.pages;

import com.orangehrmlive.demo.opensource.base.BasePage;
import com.orangehrmlive.demo.opensource.factory.EndPoint;
import com.orangehrmlive.demo.opensource.utils.ConfigUtils;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage<LoginPage> {

    // Constructor
    public LoginPage(WebDriver driver) {
        super(driver);
    }

    // Locators
    private By userNameInput = By.cssSelector("[name='username']");
    private By passwordInput = By.cssSelector("[name='password']");
    private By loginButton = By.xpath("//button[@type='submit']");
    private By loginPageHeader = By.xpath("//h5[text()='Login']");

    // Methods
    @Step("Load the Login Page")
    @Override
    public LoginPage load() {
        driver.get(ConfigUtils.getInstance().getBaseUrl() + EndPoint.LOGIN_PAGE_END_POINT);
        return this;
    }

    @Step("Fill Login form")
    public LoginPage fillLoginForm() {
        driver.findElement(userNameInput).sendKeys(ConfigUtils.getInstance().getUserName());
        driver.findElement(passwordInput).sendKeys(ConfigUtils.getInstance().getPassword());
        return this;
    }

    @Step("Click on Login Button")
    public HomePage clickOnLoginButton() {
        driver.findElement(loginButton).click();
        return new HomePage(driver);
    }

    @Step("Verify that Login Page Header is Displayed")
    public boolean isLoginPageHeaderDisplayed() {
        return driver.findElement(loginPageHeader).isDisplayed();
    }
}
