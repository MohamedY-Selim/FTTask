package com.orangehrmlive.demo.opensource.pages;

import com.orangehrmlive.demo.opensource.base.BasePage;
import com.orangehrmlive.demo.opensource.factory.EndPoint;
import com.orangehrmlive.demo.opensource.utils.ConfigUtils;
import io.qameta.allure.Step;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage<HomePage> {

    //Constructor
    public HomePage(WebDriver driver) {
        super(driver);
    }
    //

    //Elements
    @FindBy(xpath = "//h6[text()='Dashboard']")
    private WebElement homePageHeader;
    @FindBy(className = "oxd-userdropdown-tab")
    private WebElement userDropdown;
    @FindBy(xpath = "//h5[text()='Login']")
    private WebElement loginPageHeader;
    @FindBy(xpath = "//a[span[contains(@class, 'oxd-main-menu-item--name') and text()='Admin']]")
    private WebElement adminTab;
    @FindBy(xpath = "//a[span[contains(@class, 'oxd-main-menu-item--name') and text()='Recruitment']]")
    private WebElement recruitmentTab;
    //

    //Methods
    @Step("Load the Home Page")
    @Override
    public HomePage load() {
        driver.get(ConfigUtils.getInstance().getBaseUrl() + EndPoint.HOME_PAGE_END_POINT);
        return this;
    }

    @Step("Verify that Home Page Header is Displayed")
    public boolean isHomePageHeaderDisplayed() {
        return homePageHeader.isDisplayed();
    }

    @Step("Verify that User Dropdown is Displayed")
    public boolean isUserDropdownDisplayed() {
        try {
            return userDropdown.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }

    }

    @Step("Click on Admin tab on the left side menu")
    public UserManagementPage clickOnAdminTab() {
        adminTab.click();
        return new UserManagementPage(driver);
    }
    @Step("Click on Recruitment tab on the left side menu")
    public RecruitmentPage clickOnRecruitmentTab() {
        recruitmentTab.click();
        return new RecruitmentPage(driver);
    }

}
