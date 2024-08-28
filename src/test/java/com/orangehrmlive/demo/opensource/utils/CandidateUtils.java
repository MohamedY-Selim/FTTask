package com.orangehrmlive.demo.opensource.utils;

import com.github.javafaker.Faker;
import com.orangehrmlive.demo.opensource.objects.Candidate;
import java.io.File;

public class CandidateUtils {
    static Faker faker = new Faker();

    public static Candidate generateRandomCandidate() {
        String firstName = faker.name().firstName();
        String middleName = faker.name().firstName();
        String lastName = faker.name().lastName();
        int vacancy = 6;
        String email = faker.internet().emailAddress();
        String contactNumber = ConfigUtils.generateRandomPhoneNumber();
        File resume = new File("src/test/resources/files/Mohamed Yehia Resume.pdf");

        return new Candidate(firstName, middleName, lastName, vacancy, email, contactNumber, resume);
    }
}
