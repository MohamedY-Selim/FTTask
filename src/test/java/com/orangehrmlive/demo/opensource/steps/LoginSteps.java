package com.orangehrmlive.demo.opensource.steps;

import com.orangehrmlive.demo.opensource.factory.DriverFactory;
import com.orangehrmlive.demo.opensource.factory.EndPoint;
import com.orangehrmlive.demo.opensource.pages.HomePage;
import com.orangehrmlive.demo.opensource.pages.LoginPage;
import com.orangehrmlive.demo.opensource.utils.ConfigUtils;
import com.orangehrmlive.demo.opensource.utils.TestUtils;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class LoginSteps extends TestUtils {

    private WebDriver driver;
    private LoginPage loginPage;
    private HomePage homePage;

    public LoginSteps() {
        this.driver = DriverFactory.getDriver();
        this.loginPage = new LoginPage(driver);
    }

    @Given("User is at the login page")
    public void userIsAtTheLoginPage() {
        boolean isLoginPageHeaderDisplayed = loginPage.load().isLoginPageHeaderDisplayed();
        Assert.assertTrue(isLoginPageHeaderDisplayed);
    }

    @When("User fill the credentials, and login")
    public void userFillTheCredentialsAndLogin() {
        this.homePage = loginPage.fillLoginForm().clickOnLoginButton();
    }

    @Then("Home Page should be displayed")
    public void homePageShouldBeDisplayed() {
        boolean isHomePageHeaderDisplayed = homePage.isHomePageHeaderDisplayed();
        softAssert().assertTrue(isHomePageHeaderDisplayed);
        softAssert().assertEquals(homePage.getCurrentPageUrl(), ConfigUtils.getInstance().getBaseUrl() + EndPoint.HOME_PAGE_END_POINT);
        softAssert().assertAll();
    }
}
