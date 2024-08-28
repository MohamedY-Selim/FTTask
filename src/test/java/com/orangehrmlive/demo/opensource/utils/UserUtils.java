package com.orangehrmlive.demo.opensource.utils;

import com.github.javafaker.Faker;
import com.orangehrmlive.demo.opensource.objects.User;

public class UserUtils {
    static Faker faker = new Faker();

    public static User generateRandomUser() {
        String userRole = ConfigUtils.getRandomUserRole();
        String status = ConfigUtils.getRandomStatus();
        String employeeChar = ConfigUtils.getRandomChar();
        String username = faker.name().username();
        String password = ConfigUtils.generateStrongPassword();
        return new User(userRole, status, employeeChar, username, password);
    }
}