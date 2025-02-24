package com.orangehrmlive.demo.opensource.pages;

import com.orangehrmlive.demo.opensource.base.BasePage;
import com.orangehrmlive.demo.opensource.factory.EndPoint;
import com.orangehrmlive.demo.opensource.utils.ConfigUtils;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.NoSuchElementException;

public class HomePage extends BasePage<HomePage> {

    // Constructor
    public HomePage(WebDriver driver) {
        super(driver);
    }

    // Locators
    private By homePageHeader = By.xpath("//h6[text()='Dashboard']");
    private By userDropdown = By.className("oxd-userdropdown-tab");
    private By adminTab = By.xpath("//a[span[contains(@class, 'oxd-main-menu-item--name') and text()='Admin']]");
    private By recruitmentTab = By.xpath("//a[span[contains(@class, 'oxd-main-menu-item--name') and text()='Recruitment']]");

    // Methods
    @Step("Load the Home Page")
    @Override
    public HomePage load() {
        driver.get(ConfigUtils.getInstance().getBaseUrl() + EndPoint.HOME_PAGE_END_POINT);
        return this;
    }

    @Step("Verify that Home Page Header is Displayed")
    public boolean isHomePageHeaderDisplayed() {
        return driver.findElement(homePageHeader).isDisplayed();
    }

    @Step("Verify that User Dropdown is Displayed")
    public boolean isUserDropdownDisplayed() {
        try {
            return driver.findElement(userDropdown).isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    @Step("Click on Admin tab on the left side menu")
    public UserManagementPage clickOnAdminTab() {
        driver.findElement(adminTab).click();
        return new UserManagementPage(driver);
    }

    @Step("Click on Recruitment tab on the left side menu")
    public RecruitmentPage clickOnRecruitmentTab() {
        driver.findElement(recruitmentTab).click();
        return new RecruitmentPage(driver);
    }
}
