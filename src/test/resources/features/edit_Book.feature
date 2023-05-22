/*1-Librarian can edit books
2- Edit book should have following parameters: name,
author, description, category, ISBN, year.
3- ISBN must be 10 or 13 digits in length and consists of digits (0-9) and dashes (-).
4-Year parameter should have only digits (0-9)
5-Following categories should be present:
- Action and Adventure
- Anthology
- Classic
- Comic and Graphic Novel
- Crime and Detective
- Drama
- Fable
- Fairy Tale
- Fan-Fiction
- Fantasy
- Historical Fiction
- Horror
- Science Fiction
- Biography/Autobiography
- Humor
- Romance
- Short Story
- Essay
- Memoir
- Poetry
6-Librarian can select related category to see all books in that category by using book categories dropdown.
7-Librarian can use show records dropdown for getting number of entries depends the dropdown options.
8-Librarian can find book by using search box with book's Name and Author.*/
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
  @api
    Scenario:Verify that Librarian can add new books
      When I send POST request to "/add_book" endpoint with "name""isbn""year""author""book_category_id""description"
      Then status code should be 200
      And I shoul see "The book has been created." message in response body
  @api
Scenario: Verify that librarian should update book
  Then I update book's author to "Necip fazıl Kısakürek" that has 1534 id number
