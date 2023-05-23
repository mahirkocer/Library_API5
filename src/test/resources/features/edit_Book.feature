
@mik
Feature: As a librarian I should be able to edit books
  Background:
    Given user is on home page
    And user logs in as "librarian"

  Scenario: Verify that Librarian can edit books
    When user click books
    And  user click editBook button
    And user click saveChangesdButton
    Then user can see sucsecfuly saved massage
    Scenario:Verify that Librarian can add new books
      When send a get request "/add_book"
      And

