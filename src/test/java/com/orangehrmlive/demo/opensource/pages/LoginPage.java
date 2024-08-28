package com.orangehrmlive.demo.opensource.pages;

import com.orangehrmlive.demo.opensource.base.BasePage;
import com.orangehrmlive.demo.opensource.factory.EndPoint;
import com.orangehrmlive.demo.opensource.utils.ConfigUtils;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage<LoginPage> {

    //Constructor
    public LoginPage(WebDriver driver) {
        super(driver);
    }
    //

    //Elements
    @FindBy(css = "[name='username']")
    private WebElement userNameInput;
    @FindBy(css = "[name='password']")
    private WebElement passwordInput;
    @FindBy(xpath = "//button[@type='submit']")
    private WebElement loginButton;
    @FindBy(xpath = "//h5[text()='Login']")
    private WebElement loginPageHeader;
    //

    //Methods
    @Step("Load the Login Page")
    @Override
    public LoginPage load() {
        driver.get(ConfigUtils.getInstance().getBaseUrl() + EndPoint.LOGIN_PAGE_END_POINT);
        return this;
    }

    @Step("Fill Login form")
    public LoginPage fillLoginForm() {
        userNameInput.sendKeys(ConfigUtils.getInstance().getUserName());
        passwordInput.sendKeys(ConfigUtils.getInstance().getPassword());
        return this;
    }

    @Step("Click on Login Button")
    public HomePage clickOnLoginButton() {
        loginButton.click();
        return new HomePage(driver);
    }

    @Step("Verify that Login Page Header is Displayed")
    public boolean isLoginPageHeaderDisplayed() {
        return loginPageHeader.isDisplayed();
    }
}
