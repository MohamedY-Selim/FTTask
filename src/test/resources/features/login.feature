Feature: Login Feature
  Scenario: User should be able to login
    Given User is at the login page
    When User fill the credentials, and login
    Then Home Page should be displayed
