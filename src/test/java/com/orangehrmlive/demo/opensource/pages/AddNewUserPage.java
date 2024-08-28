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
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class AddNewUserPage extends BasePage<AddNewUserPage> {
    User user;

    //Driver
    WebDriver driver;

    //Constructor
    public AddNewUserPage(WebDriver driver) {
        super(driver);
    }
    //

    //Elements
    @FindBy(xpath = "//h6[text()='Add User']")
    private WebElement addUserHeader;
    @FindBy(xpath = "//label[text()='User Role']/following::div[contains(@class, 'oxd-select-text-input')][1]")
    private WebElement userRoleDropDown;
    @FindBy(xpath = "//label[text()='Status']/following::div[contains(@class, 'oxd-select-text-input')][1]")
    private WebElement statusDropDown;
    @FindBy(xpath = "//label[text()='Employee Name']/following::div[contains(@class, 'oxd-autocomplete-text-input')][1]/input")
    private WebElement employeeNameInput;
    @FindBy(xpath = "//label[text()='Username']/ancestor::div[contains(@class, 'oxd-input-group')]/descendant::input")
    private WebElement userNameInput;
    @FindBy(xpath = "//label[text()='Password']/following::input[contains(@class, 'oxd-input')][1]")
    private WebElement passwordInput;
    @FindBy(xpath = "//label[text()='Confirm Password']/following::input[contains(@class, 'oxd-input')][1]")
    private WebElement confirmPasswordInput;
    @FindBy(xpath = "//button[@type='submit']")
    private WebElement saveButton;
    @FindBy(xpath = "//div[@role='listbox']")
    private WebElement listOfOptions;

    //

    //Methods
    @Step("Load the User Management Page")
    @Override
    public AddNewUserPage load() {
        driver.get(ConfigUtils.getInstance().getBaseUrl() + EndPoint.HOME_PAGE_END_POINT);
        return this;
    }

    @Step("Verify that Add User Form Header is Displayed")
    public boolean isAddUserFormHeaderDisplayed() {
        return addUserHeader.isDisplayed();
    }

    @Step("Fill New User Data")
    public AddNewUserPage fillNewUserData() {
        user = UserUtils.generateRandomUser();
        selectFromTheDropDown(userRoleDropDown, user.getUserRole());
        selectFromTheDropDown(statusDropDown, user.getStatus());
        employeeNameInput.sendKeys(user.getEmployeeName());
        explicitWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@role='listbox']//span[contains(text(), '" + user.getEmployeeName() + "')][1]")));
        selectFromTheDropDownWithKeys();
        String employeeName = employeeNameInput.getAttribute("value");
        user.setEmployeeName(employeeName);
        userNameInput.sendKeys(user.getUserName());
        passwordInput.sendKeys(user.getPassword());
        confirmPasswordInput.sendKeys(user.getPassword());
        return this;
    }

    @Step("Click on Save Button")
    public User clickOnSaveButton() {
        saveButton.click();
        return user;
    }

}
