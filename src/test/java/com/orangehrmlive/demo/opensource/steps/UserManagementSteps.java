package com.orangehrmlive.demo.opensource.steps;

import com.orangehrmlive.demo.opensource.factory.DriverFactory;
import com.orangehrmlive.demo.opensource.factory.EndPoint;
import com.orangehrmlive.demo.opensource.objects.User;
import com.orangehrmlive.demo.opensource.pages.AddNewUserPage;
import com.orangehrmlive.demo.opensource.pages.HomePage;
import com.orangehrmlive.demo.opensource.pages.UserManagementPage;
import com.orangehrmlive.demo.opensource.utils.ConfigUtils;
import com.orangehrmlive.demo.opensource.utils.TestUtils;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class UserManagementSteps extends TestUtils {
    private static User user;
    private WebDriver driver;
    private HomePage homePage;
    private UserManagementPage userManagementPage;
    private AddNewUserPage addNewUserPage;
    private int userCountBefore;
    private int userCountAfter;

    public UserManagementSteps() {
        this.driver = DriverFactory.getDriver();
        this.homePage = new HomePage(driver);
        this.userManagementPage = new UserManagementPage(driver);
    }

    // Scenario 1: Admin should be able to navigate to User Management Page
    @Given("Admin is at the home page")
    public void adminIsAtTheHomePage() {
        Assert.assertTrue(homePage.load().isHomePageHeaderDisplayed());
    }

    @When("Admin click on Admin tab on the left side menu")
    public void adminClickOnAdminTabOnTheLeftSideMenu() {
        homePage.clickOnAdminTab();
    }

    @Then("User Management Page should be displayed")
    public void userManagementPageShouldBeDisplayed() {
        boolean isUserManagementPageHeaderDisplayed = userManagementPage.isUserManagementPageHeaderDisplayed();
        softAssert().assertTrue(isUserManagementPageHeaderDisplayed);
        softAssert().assertEquals(userManagementPage.getCurrentPageUrl(), ConfigUtils.getInstance().getBaseUrl() + EndPoint.USER_MANAGEMENT_PAGE_END_POINT);
        softAssert().assertAll();
    }

    // Scenario 2: Admin should be able to add a new user
    @Given("Admin is at the user management page")
    public void adminIsAtTheUserManagementPage() {
        userCountBefore = userManagementPage
                .load()
                .getUserCount();
    }

    @When("Admin Click on add button")
    public void adminClickOnAddButton() {
        this.addNewUserPage = userManagementPage.clickOnAddButton();
        boolean isAddUserFormHeaderDisplayed = addNewUserPage.isAddUserFormHeaderDisplayed();
        softAssert().assertTrue(isAddUserFormHeaderDisplayed);
        softAssert().assertEquals(addNewUserPage.getCurrentPageUrl(), ConfigUtils.getInstance().getBaseUrl() + EndPoint.ADD_NEW_USER_PAGE_END_POINT);
    }

    @And("Fill new user data, and save")
    public void fillNewUserDataAndSave() {
        user = addNewUserPage
                .fillNewUserData()
                .clickOnSaveButton();

    }

    @Then("Number of users records should be increased by one")
    public void numberOfUsersRecordsShouldBeIncreasedByOne() {
        userCountAfter = userManagementPage.getUserCount();
        Assert.assertEquals(userCountAfter, userCountBefore + 1);
    }

    // Scenario 3: Admin should be able to delete a user
    @When("Admin fill user data, and search")
    public void adminFillUserDataAndSearch() {
        userManagementPage.fillUserDataToSearch(user);
    }

    @When("Click on Delete button, and confirm")
    public void clickOnDeleteButtonAndConfirm() {
        userManagementPage.deleteNewUser(user);
    }

    @Then("Number of users records should be decreased by one")
    public void numberOfUsersRecordsShouldBeDecreasedByOne() {
        userCountAfter = userManagementPage.load().getUserCount();
        Assert.assertEquals(userCountAfter, userCountBefore - 1);
    }

    @Before(value = "@requiresUserAdd", order = 2)
    public void addUser() {
        if (!isUserAdded()) {
            adminIsAtTheUserManagementPage();
            adminClickOnAddButton();
            fillNewUserDataAndSave();
            numberOfUsersRecordsShouldBeIncreasedByOne();
        }
    }

    private boolean isUserAdded() {
        return user != null;
    }

}
