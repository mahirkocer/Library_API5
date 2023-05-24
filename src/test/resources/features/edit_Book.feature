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


  @api
  Scenario:Verify that Librarian can add new books
    When I send POST request to "/add_book" endpoint with "name""isbn""year""author""book_category_id""description"
    Then status code should be 200
    And I shoul see "The book has been created." message in response body

  @api@mik
  Scenario:Verify that Librarian can add new books
    When I send POST request to "/add_book" with following information
      | name             | yasko    |
      | isbn             | 212565   |
      | year             | 2021     |
      | author           | mahmah   |
      | book_category_id | 3        |
      | description      | ehjryjtj |

    Then status code should be 200
    And I shoul see "The book has been created." message in response body

  @api
  Scenario: Verify that librarian should update book
    Then I update book's author to "Necip fazıl Kısakürek" that has 1534 id number

  Scenario: Verify that edit book should have following parameters
    When user click books
    And  user click editBook button
    Then User should see following parameters
      | Book Name     |
      | ISBN          |
      | Year          |
      | Author        |
      | Book Category |
      | Description   |
Scenario: Verify that Following categories should be present
  When user click books
 Then User should verify that Following categories present
   | ALL                     |
   | Action and Adventure    |
   | Anthology               |
   | Classic                 |
   | Comic and Graphic Novel |
   | Crime and Detective     |
   | Drama                   |
   | Fable                   |
   | Fairy Tale              |
   | Fan-Fiction             |
   | Fantasy                 |
   | Historical Fiction      |
   | Horror                  |
   | Science Fiction         |
   | Biography/Autobiography |
   | Humor                   |
   | Romance                 |
   | Short Story             |
   | Essay                   |
   | Memoir                  |
   | Poetry                  |
  @api
Scenario: erify that Following categories should be present
  When I send to request "/get_book_categories" endpoint
  Then I should verify that Following categories present
    | Action and Adventure    |
    | Anthology               |
    | Classic                 |
    | Comic and Graphic Novel |
    | Crime and Detective     |
    | Drama                   |
    | Fable                   |
    | Fairy Tale              |
    | Fan-Fiction             |
    | Fantasy                 |
    | Historical Fiction      |
    | Horror                  |
    | Science Fiction         |
    | Biography/Autobiography |
    | Humor                   |
    | Romance                 |
    | Short Story             |
    | Essay                   |
    | Memoir                  |
    | Poetry                  |
    Scenario: Librarian can select related category to see all books in that category by using book categories dropdown.
      When user click books
      And user select "Classic" category
      Then user should only see all classic books
