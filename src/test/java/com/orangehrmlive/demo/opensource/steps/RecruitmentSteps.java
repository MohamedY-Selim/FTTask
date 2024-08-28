package com.orangehrmlive.demo.opensource.steps;

import com.orangehrmlive.demo.opensource.api.CandidateApi;
import com.orangehrmlive.demo.opensource.factory.DriverFactory;
import com.orangehrmlive.demo.opensource.factory.EndPoint;
import com.orangehrmlive.demo.opensource.pages.HomePage;
import com.orangehrmlive.demo.opensource.pages.RecruitmentPage;
import com.orangehrmlive.demo.opensource.utils.ConfigUtils;
import com.orangehrmlive.demo.opensource.utils.TestUtils;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class RecruitmentSteps extends TestUtils {
    private WebDriver driver;
    private HomePage homePage;
    private RecruitmentPage recruitmentPage;
    private int candidateCountBefore;
    private int candidateCountAfter;
    private static Integer newUserId;
    CandidateApi candidateApi;

    public RecruitmentSteps() {
        this.driver = DriverFactory.getDriver();
        this.homePage = new HomePage(driver);
        this.recruitmentPage = new RecruitmentPage(driver);
        this.candidateApi = new CandidateApi(driver);
    }

    // Scenario 1: Admin should be able to navigate to Recruitment Page
    @When("Admin click on Recruitment tab on the left side menu")
    public void adminClickOnRecruitmentTabOnTheLeftSideMenu() {
        homePage.clickOnRecruitmentTab();
    }

    @Then("Recruitment Page should be displayed")
    public void recruitmentPageShouldBeDisplayed() {
        boolean isRecruitmentPageHeaderDisplayed = recruitmentPage.isRecruitmentPageHeaderDisplayed();
        softAssert().assertTrue(isRecruitmentPageHeaderDisplayed);
        softAssert().assertEquals(recruitmentPage.getCurrentPageUrl(), ConfigUtils.getInstance().getBaseUrl() + EndPoint.Recruitment_PAGE_END_POINT);
        softAssert().assertAll();
    }

    // Scenario 2: Admin should be able to add a new Candidate using api
    @Given("Admin is at the Recruitment page")
    public void adminIsAtTheRecruitmentPage() {
        candidateCountBefore = recruitmentPage
                .load()
                .getCandidatesCount();

    }

    @When("Admin add a new candidate using api")
    public void adminAddANewCandidateUsingApi() {
        newUserId = candidateApi.addCandidate();
    }

    @Then("Number of candidates records should be increased by one")
    public void numberOfCandidatesRecordsShouldBeIncreasedByOne() {
        candidateCountAfter = recruitmentPage.load().getCandidatesCount();
        Assert.assertEquals(candidateCountAfter, candidateCountBefore + 1);
    }

    // Scenario 3: Admin should be able to delete the new Candidate using api

    @When("Admin delete the new candidate using api")
    public void adminDeleteTheNewCandidateUsingApi() {
        candidateApi.deleteCandidate(newUserId);
    }

    @Then("Number of candidates records should be decreased by one")
    public void numberOfCandidatesRecordsShouldBeDecreasedByOne() {
        candidateCountAfter = recruitmentPage.load().getCandidatesCount();
        Assert.assertEquals(candidateCountAfter, candidateCountBefore - 1);
    }

    @Before(value = "@requiresCandidateAdd", order = 2)
    public void addUser() {
        if (!isUserAdded()) {
            adminIsAtTheRecruitmentPage();
            adminAddANewCandidateUsingApi();
            numberOfCandidatesRecordsShouldBeIncreasedByOne();
        }
    }

    private boolean isUserAdded() {
        return newUserId != null;
    }

}
