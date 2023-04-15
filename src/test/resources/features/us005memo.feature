Feature: Add User Function US-005

  Background: user logs into library page
    Given user is on home page
    Then user logs in as "librarian"

  @wipi
  Scenario: verify Librarian can add new user
    When user click add user button
    Then user fill out new user info
    And user clicks save button