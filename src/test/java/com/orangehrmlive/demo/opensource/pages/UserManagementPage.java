package com.orangehrmlive.demo.opensource.pages;

import com.orangehrmlive.demo.opensource.base.BasePage;
import com.orangehrmlive.demo.opensource.objects.User;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class UserManagementPage extends BasePage<UserManagementPage> {

    // Constructor
    public UserManagementPage(WebDriver driver) {
        super(driver);
    }

    // Locators
    private By userRoleDropdown = By.xpath("//label[contains(text(),'User Role')]/following::div[@class='oxd-select-text-input']");
    private By statusDropdown = By.xpath("//label[contains(text(),'Status')]/following::div[contains(@class,'oxd-select-text-input')]");

    // Methods
    @Step("Fill User Data to search")
    public UserManagementPage fillUserDataToSearch(User user) {
        selectFromTheDropDown(userRoleDropdown, user.getUserRole());
        selectFromTheDropDown(statusDropdown, user.getStatus());
        return this;
    }
}
