Feature: Barrow Book Function US-007

  1-Student can borrow books in books page.
  2-Book cannot be borrowed twice at the same time.
  3-Student should have a history of borrowed books when navigate to borrowing books page.
  4-Student can return books when navigate to borrowing books page.
  5-Student can select related category to see all books in that category by using book categories dropdown.
  6-Student can use show records dropdown for getting number of entries depends the dropdown options.
  7-Student can find book by using search box with book's Name and Author.

#  Background:
#    Given user is on home page
#    And user logs in as "student"

  @barrow_book
  Scenario: Verify that user can borrow books in books page
    When users click the books menu
    And users enters book "Diceros bicornis" into seacrh box
    And user click the Barrow Book button
    Then success message is displayed

  @barrowBook
  Scenario:
    When  user can barrow a book

  @AddBook @api
  Scenario: Librarian can create a book
    When user login as a librarian to api
    And user send a request to create a book
    Then verify response

  @iframe
  Scenario: Iframes
    When user navigate to the page
    Then verify teext
    Then switch the inner iframe

    @database
    When user creates a connection and get all user



