@requiresLogin
Feature: Recruitment Feature

  Scenario: Admin should be able to navigate to Recruitment Page
    Given Admin is at the home page
    When Admin click on Recruitment tab on the left side menu
    Then Recruitment Page should be displayed

  Scenario: Admin should be able to add a new candidate using api, and number of candidates records should be increased by one
    Given Admin is at the Recruitment page
    When Admin add a new candidate using api
    Then Number of candidates records should be increased by one

  @requiresCandidateAdd
  Scenario: Admin should be able to delete the new candidate using api, and number of candidates records should be decreased by one
    Given Admin is at the Recruitment page
    When Admin delete the new candidate using api
    Then Number of candidates records should be decreased by one
