package com.orangehrmlive.demo.opensource.pages;

import com.orangehrmlive.demo.opensource.base.BasePage;
import com.orangehrmlive.demo.opensource.factory.EndPoint;
import com.orangehrmlive.demo.opensource.objects.User;
import com.orangehrmlive.demo.opensource.utils.ConfigUtils;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class RecruitmentPage extends BasePage<RecruitmentPage> {

    // Constructor
    public RecruitmentPage(WebDriver driver) {
        super(driver);
    }

    // Locators
    private By recruitmentPageHeader = By.cssSelector("h6.oxd-text.oxd-text--h6.oxd-topbar-header-breadcrumb-module");
    private By usersCount = By.xpath("//div[@class='orangehrm-horizontal-padding orangehrm-vertical-padding']/span[@class='oxd-text oxd-text--span']");
    private By addButton = By.xpath("//button[contains(@class, 'oxd-button') and contains(., 'Add')]");
    private By userNameInput = By.xpath("//label[text()='Username']/following::input[1]");
    private By userRoleDropdown = By.xpath("//label[contains(text(),'User Role')]/following::div[@class='oxd-select-text-input']");
    private By employeeNameInput = By.xpath("//label[contains(text(),'Employee Name')]/following::input[@placeholder='Type for hints...']");
    private By statusDropdown = By.xpath("//label[contains(text(),'Status')]/following::div[contains(@class,'oxd-select-text-input')]");
    private By searchButton = By.xpath("//button[@type='submit']");

    // Methods
    @Step("Load the Recruitment Page")
    @Override
    public RecruitmentPage load() {
        driver.get(ConfigUtils.getInstance().getBaseUrl() + EndPoint.Recruitment_PAGE_END_POINT);
        return this;
    }

    @Step("Verify that Recruitment Header is Displayed")
    public boolean isRecruitmentPageHeaderDisplayed() {
        return driver.findElement(recruitmentPageHeader).isDisplayed();
    }

    @Step("Get Current candidates count")
    public int getCandidatesCount() {
        explicitWait().until(ExpectedConditions.visibilityOfElementLocated(usersCount));
        return Integer.parseInt(driver.findElement(usersCount).getText().replaceAll("[^0-9]", ""));
    }

    @Step("Click on Add Button")
    public AddNewUserPage clickOnAddButton() {
        driver.findElement(addButton).click();
        return new AddNewUserPage(driver);
    }

    @Step("Fill User Data to search")
    public RecruitmentPage fillUserDataToSearch(User user) {
        driver.findElement(userNameInput).sendKeys(user.getUserName());
        selectFromTheDropDown(userRoleDropdown, user.getUserRole());
        driver.findElement(employeeNameInput).sendKeys(user.getEmployeeName());
        selectSpecificOptionFromTheDropDown(user.getEmployeeName());
        selectFromTheDropDown(statusDropdown, user.getStatus());
        driver.findElement(searchButton).click();
        return this;
    }

    @Step("Delete the new User")
    public RecruitmentPage deleteNewUser(User user) {
        By deleteButtonLocator = By.xpath("//div[@class='oxd-table-row oxd-table-row--with-border' and .//div[text()='" + user.getUserName() + "']]//button[contains(@class, 'oxd-icon-button') and .//i[contains(@class, 'bi-trash')]]");
        By confirmDeleteButtonLocator = By.xpath("//div[contains(@class, 'orangehrm-modal-footer')]//button[contains(@class, 'oxd-button--label-danger')]");

        explicitWait().until(ExpectedConditions.visibilityOfElementLocated(deleteButtonLocator)).click();
        explicitWait().until(ExpectedConditions.visibilityOfElementLocated(confirmDeleteButtonLocator)).click();
        return this;
    }
}
