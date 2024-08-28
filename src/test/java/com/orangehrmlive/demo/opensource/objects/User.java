package com.orangehrmlive.demo.opensource.objects;

public class User {
    //Attributes
    private String employeeName;
    private String userName;
    private String password;
    private String userRole;


    private String status;


    //Constructor
    public User(String userRole, String status, String employeeName, String userName, String password) {
        this.userRole = userRole;
        this.status = status;
        this.employeeName = employeeName;
        this.userName = userName;
        this.password = password;
    }

    //Getters & Setters
    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
