package com.orangehrmlive.demo.opensource.objects;

import java.io.File;

public class Candidate {
    // Attributes
    private String firstName;
    private String middleName;
    private String lastName;
    private int vacancy;
    private String email;
    private String contactNumber;
    private File resume;

    // Constructor
    public Candidate(String firstName, String middleName, String lastName, int vacancy, String email, String contactNumber, File resume) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.vacancy = vacancy;
        this.email = email;
        this.contactNumber = contactNumber;
        this.resume = resume;
    }

    // Getters & Setters
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getVacancy() {
        return vacancy;
    }

    public void setVacancy(int vacancy) {
        this.vacancy = vacancy;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public File getResume() {
        return resume;
    }

    public void setResume(File resume) {
        this.resume = resume;
    }
}
