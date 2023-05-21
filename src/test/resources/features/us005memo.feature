@FIX10-501 @hi
Feature: Add User Function US-005
  1-Librarian can add new user
  2-Add user should have following parameters : Full Name, Password, Email, User Group, Status, Start Date, End Date, Address
  3-User Group should have 2 user types as Librarian and Students
  4-Status should have ACTIVE and INACTIVE

  Background:
    Given user is on home page
    Then user logs in as "librarian"

  @db @FIX10-516
  Scenario: Verify Librarian can add new user (AC-1)
    When user click add user button
    Then user fill out new user info
    And user clicks save button
    Then user verifies added name is seen in DB

  @api @FIX10-518
  Scenario: Verify "Add user" module should have all necessary parameters (AC-2)
    Then user verifies added user has all params by checking API

  @api @db @FIX10-519
  Scenario: Verify User Group should have 2 user types as Librarian and Students (AC-3)
    Then user checks groups types from API
    And user checks groups from DB

  @api @FIX10-520
  Scenario: Verify user status should have active and inactive options
    Then user check status codes from DB