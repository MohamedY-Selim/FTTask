package com.orangehrmlive.demo.opensource.pages;

import com.orangehrmlive.demo.opensource.base.BasePage;
import com.orangehrmlive.demo.opensource.factory.EndPoint;
import com.orangehrmlive.demo.opensource.objects.User;
import com.orangehrmlive.demo.opensource.utils.ConfigUtils;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class RecruitmentPage extends BasePage<RecruitmentPage> {


    //Constructor
    public RecruitmentPage(WebDriver driver) {
        super(driver);
    }
    //

    //Elements
    @FindBy(css = "h6.oxd-text.oxd-text--h6.oxd-topbar-header-breadcrumb-module")
    private WebElement recruitmentPageHeader;
    @FindBy(xpath = "//div[@class='orangehrm-horizontal-padding orangehrm-vertical-padding']/span[@class='oxd-text oxd-text--span']")
    private WebElement usersCount;
    @FindBy(xpath = "//button[contains(@class, 'oxd-button') and contains(., 'Add')]")
    private WebElement addButton;
    @FindBy(xpath = "//label[text()='Username']/following::input[1]")
    private WebElement userNameInput;
    @FindBy(xpath = "//label[contains(text(),'User Role')]/following::div[@class='oxd-select-text-input']")
    private WebElement userRoleDropdown;
    @FindBy(xpath = "//label[contains(text(),'Employee Name')]/following::input[@placeholder='Type for hints...']")
    private WebElement employeeNameInput;
    @FindBy(xpath = "//label[contains(text(),'Status')]/following::div[contains(@class,'oxd-select-text-input')]")
    private WebElement statusDropdown;
    @FindBy(xpath = "//button[@type='submit']")
    private WebElement searchButton;
    //

    //Methods
    @Step("Load the Recruitment Page")
    @Override
    public RecruitmentPage load() {
        driver.get(ConfigUtils.getInstance().getBaseUrl() + EndPoint.Recruitment_PAGE_END_POINT);
        return this;
    }

    @Step("Verify that Recruitment Header is Displayed")
    public boolean isRecruitmentPageHeaderDisplayed() {
        return recruitmentPageHeader.isDisplayed();
    }

    @Step("Get Current candidates count")
    public int getCandidatesCount() {
        explicitWait().until(ExpectedConditions.visibilityOf(usersCount));
        return Integer.parseInt(usersCount.getText().replaceAll("[^0-9]", ""));
    }

    @Step("Click on Add Button")
    public AddNewUserPage clickOnAddButton() {
        addButton.click();
        return new AddNewUserPage(driver);
    }

    @Step("Fill User Data to search")
    public RecruitmentPage fillUserDataToSearch(User user) {
        userNameInput.sendKeys(user.getUserName());
        selectFromTheDropDown(userRoleDropdown, user.getUserRole());
        employeeNameInput.sendKeys(user.getEmployeeName());
        selectSpecificOptionFromTheDropDown(user.getEmployeeName());
        selectFromTheDropDown(statusDropdown, user.getStatus());
        searchButton.click();
        return this;
    }

    @Step("Delete the new User")
    public RecruitmentPage deleteNewUser(User user) {
        WebElement deleteButton = explicitWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='oxd-table-row oxd-table-row--with-border' and .//div[text()='" + user.getUserName() + "']]//button[contains(@class, 'oxd-icon-button') and .//i[contains(@class, 'bi-trash')]]")));
        deleteButton.click();
        WebElement confirmDeleteButton = explicitWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class, 'orangehrm-modal-footer')]//button[contains(@class, 'oxd-button--label-danger')]")));
        confirmDeleteButton.click();
        return this;
    }

}
