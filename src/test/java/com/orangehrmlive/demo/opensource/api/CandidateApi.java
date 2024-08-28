package com.orangehrmlive.demo.opensource.api;

import com.orangehrmlive.demo.opensource.objects.Candidate;
import com.orangehrmlive.demo.opensource.utils.CandidateUtils;
import com.orangehrmlive.demo.opensource.utils.ConfigUtils;
import com.orangehrmlive.demo.opensource.factory.EndPoint;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;

import static io.restassured.RestAssured.given;

public class CandidateApi {
    private WebDriver driver;
    private Response response;
    private String sessionCookie;

    public CandidateApi(WebDriver driver) {
        this.driver = driver;
    }

    public String getSessionCookie() {
        Cookie sessionCookie = driver.manage().getCookieNamed("orangehrm");
        if (sessionCookie != null) {
            return sessionCookie.getValue();
        } else {
            throw new RuntimeException("Session cookie not found.");
        }
    }

    public int addCandidate() {
        sessionCookie = getSessionCookie();
        Candidate candidate = CandidateUtils.generateRandomCandidate();
        response = given()
                .baseUri(ConfigUtils.getInstance().getBaseUrl())
                .header("Cookie", "orangehrm=" + sessionCookie)
                .multiPart("firstName", candidate.getFirstName())
                .multiPart("middleName", candidate.getMiddleName())
                .multiPart("lastName", candidate.getLastName())
                .multiPart("email", candidate.getEmail())
                .multiPart("contactNumber", candidate.getContactNumber())
                .multiPart("vacancyId", candidate.getVacancy())
                .multiPart("resume", candidate.getResume())
                .when()
                .post(EndPoint.CANDIDATE_API)
                .then()
                .extract().response();
        if (response.statusCode() != 200) {
            throw new RuntimeException("Something went wrong in adding the candidate");
        }
        JsonPath jsonPath = response.jsonPath();
        int candidateId = jsonPath.getInt("data.id");
        return candidateId;
    }

    public void deleteCandidate(int id) {
        sessionCookie = getSessionCookie();
        String deletePayload = String.format("{\"ids\":[%d]}", id);
        response = given()
                .baseUri(ConfigUtils.getInstance().getBaseUrl())
                .header("Content-Type", "application/json")
                .header("Cookie", "orangehrm=" + sessionCookie)
                .body(deletePayload)
                .when()
                .delete(EndPoint.CANDIDATE_API)
                .then()
                .extract().response();

        // Check the response status code
        if (response.statusCode() != 200) {
            throw new RuntimeException("Something went wrong in deleting the candidate");
        }
    }

}
