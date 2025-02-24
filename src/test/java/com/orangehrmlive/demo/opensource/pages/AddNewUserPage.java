package com.orangehrmlive.demo.opensource.pages;

import com.orangehrmlive.demo.opensource.base.BasePage;
import com.orangehrmlive.demo.opensource.factory.EndPoint;
import com.orangehrmlive.demo.opensource.objects.User;
import com.orangehrmlive.demo.opensource.utils.ConfigUtils;
import com.orangehrmlive.demo.opensource.utils.UserUtils;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class AddNewUserPage extends BasePage<AddNewUserPage> {
    private User user;

    // Constructor
    public AddNewUserPage(WebDriver driver) {
        super(driver);
    }

    // Locators
    private By addUserHeader = By.xpath("//h6[text()='Add User']");
    private By userRoleDropDown = By.xpath("//label[text()='User Role']/following::div[contains(@class, 'oxd-select-text-input')][1]");
    private By statusDropDown = By.xpath("//label[text()='Status']/following::div[contains(@class, 'oxd-select-text-input')][1]");
    private By employeeNameInput = By.xpath("//label[text()='Employee Name']/following::div[contains(@class, 'oxd-autocomplete-text-input')][1]/input");
    private By userNameInput = By.xpath("//label[text()='Username']/ancestor::div[contains(@class, 'oxd-input-group')]/descendant::input");
    private By passwordInput = By.xpath("//label[text()='Password']/following::input[contains(@class, 'oxd-input')][1]");
    private By confirmPasswordInput = By.xpath("//label[text()='Confirm Password']/following::input[contains(@class, 'oxd-input')][1]");
    private By saveButton = By.xpath("//button[@type='submit']");

    // Methods
    @Step("Load the User Management Page")
    @Override
    public AddNewUserPage load() {
        driver.get(ConfigUtils.getInstance().getBaseUrl() + EndPoint.HOME_PAGE_END_POINT);
        return this;
    }

    @Step("Verify that Add User Form Header is Displayed")
    public boolean isAddUserFormHeaderDisplayed() {
        return driver.findElement(addUserHeader).isDisplayed();
    }

    @Step("Fill New User Data")
    public AddNewUserPage fillNewUserData() {
        user = UserUtils.generateRandomUser();
        selectFromTheDropDown(userRoleDropDown, user.getUserRole());
        selectFromTheDropDown(statusDropDown,
