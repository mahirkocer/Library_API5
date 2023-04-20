@FIX10-503
Feature: Edit User Function

  User Story: As a librarian I should be able to edit users

Background: For the scenarios in the feature file, user is expected to be logged in as librarian
  Given user is on home page
  And user logs in as "librarian"
  And user goes to Users page

@FIX10-523
Scenario: Verify librarian can edit new user
  When librarian clicks Edit User button
  And librarian updates all the information
  And librarian clicks Save Changes button
  Then librarian should see the updated version in DB

@FIX10-524 @api
Scenario: Verify 'Edit User' information has all parameters
  When librarian clicks Edit User button
  And librarian updates all the information
  And librarian clicks Save Changes button
  Then librarian should see the updated version with all parameters in API