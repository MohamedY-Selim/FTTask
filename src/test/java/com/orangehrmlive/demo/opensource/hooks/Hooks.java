package com.orangehrmlive.demo.opensource.hooks;

import com.orangehrmlive.demo.opensource.factory.DriverFactory;
import com.orangehrmlive.demo.opensource.objects.User;
import com.orangehrmlive.demo.opensource.pages.HomePage;
import com.orangehrmlive.demo.opensource.steps.LoginSteps;
import com.orangehrmlive.demo.opensource.steps.UserManagementSteps;
import io.cucumber.java.*;
import io.qameta.allure.Allure;
import io.qameta.allure.AllureLifecycle;
import io.qameta.allure.Step;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class Hooks {

    private static WebDriver driver;
    private static User user;
    public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
    public static final String ANSI_RED_BACKGROUND = "\u001B[41m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RESET = "\u001B[0m";

    @BeforeAll
    public static void setup() {
        driver = DriverFactory.initializeDriver();
    }
//
//    @AfterStep
//    @Step("Take screenshot after each step if failed")
//    public void afterStep(Scenario scenario) {
//        if (scenario.isFailed()) {
//            File destFile = new File("target/screenshots/failed_" + scenario.getName() + ".png");
//            takeScreenshot(destFile);
//        }
//    }

    @After
    @Step("Log scenario result")
    public void afterScenario(Scenario scenario) {
        String scenarioName = scenario.getName();
        boolean status = !scenario.isFailed();

        if (status) {
            System.out.println(ANSI_GREEN_BACKGROUND + ANSI_BLACK + scenarioName + " Scenario Succeed" + ANSI_RESET);
        } else {
            System.out.println(ANSI_RED_BACKGROUND + ANSI_BLACK + scenarioName + " Scenario Failed" + ANSI_RESET);
        }
        File destFile = new File("target/screenshots/failed_" + scenario.getName() + ".png");
        takeScreenshot(destFile);

        // Handle window management after scenario
        List<String> allWindows = new ArrayList<>(driver.getWindowHandles());

        // Close all tabs except the first one
        for (int i = 1; i < allWindows.size(); i++) {
            driver.switchTo().window(allWindows.get(i));
            driver.close();
        }

        // Switch back to the first tab
        driver.switchTo().window(allWindows.get(0));

        // Clear all cookies if the scenario has a certain tag or condition
        if (scenario.getSourceTagNames().contains("Initial")) {
            driver.manage().deleteAllCookies();
        }
    }

    @Step("Driver quit")
    @AfterAll
    public static void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    public void takeScreenshot(File destFile) {
        File file = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(file, destFile);
            InputStream is = new FileInputStream(destFile);
            Allure.addAttachment("screenshot", is);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Before(value = "@requiresLogin", order = 1)
    public void login() {
        if (!isUserLoggedIn()) {
            LoginSteps loginSteps = new LoginSteps();
            loginSteps.userIsAtTheLoginPage();
            loginSteps.userFillTheCredentialsAndLogin();
            loginSteps.homePageShouldBeDisplayed();
        }
    }

    private boolean isUserLoggedIn() {
        HomePage homePage = new HomePage(driver);
        return homePage.isUserDropdownDisplayed();
    }

}
