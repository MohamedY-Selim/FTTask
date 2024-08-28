@requiresLogin
Feature: User Management Feature

  Scenario: Admin should be able to navigate to User Management Page
    Given Admin is at the home page
    When Admin click on Admin tab on the left side menu
    Then User Management Page should be displayed

  Scenario: Admin should be able to add a new user, and number of users records should be increased by one
    Given Admin is at the user management page
    When Admin Click on add button
    And Fill new user data, and save
    Then Number of users records should be increased by one

  @requiresUserAdd
  Scenario: Admin should be able to delete a user, and number of users records should be decreased by one
    Given Admin is at the user management page
    When Admin fill user data, and search
    And Click on Delete button, and confirm
    Then Number of users records should be decreased by one
