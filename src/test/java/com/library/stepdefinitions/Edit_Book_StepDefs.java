package com.library.stepdefinitions;

import com.library.pages.BookEditPage;
import com.library.utilities.*;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import static io.restassured.RestAssured.*;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Edit_Book_StepDefs {
    BookEditPage bookEditPage=new BookEditPage();
    Response response;
    @When("user click books")
    public void user_click_books() throws InterruptedException {
        BrowserUtils.waitForClickablility( bookEditPage.books,15);
        bookEditPage.books.click();
        Thread.sleep(3000);

    }


    @And("user click saveChangesdButton")
    public void userClickSaveChangesdButton() throws InterruptedException {
        bookEditPage.saveButton.click();

        Thread.sleep(3000);
    }

    @And("user click editBook button")
    public void userClickEditBookButton() throws InterruptedException {
        bookEditPage.editButton.click();

        Thread.sleep(3000);



    }

    @Then("user can see sucsecfuly saved massage")
    public void userCanSeeSucsecfulySavedMassage() {
        Assertions.assertTrue(bookEditPage.savedBookWrite.isDisplayed());


    }

    @Given("I logged Bookit api using {string} and {string}")
    public void iLoggedBookitApiUsingAnd(String email, String password) {


    }

    @When("I send POST request to {string} endpoint with {string}{string}{string}{string}{string}{string}")

    public void iSendPOSTRequestToEndpointWith(String endPoint, String name, String isbn, String year, String author, String book_category, String description) {


      response = given().accept(ContentType.JSON)
                .header("x-library-token", Hooks.token)
                .formParam("name", name)
                .formParam("isbn", isbn)
                .formParam("year", year)
                .formParam("author", author)
                .formParam("book_category_id", 3)
                .formParam("description", description)
                .when().post(endPoint);

    }

    @Then("status code should be {int}")
    public void statusCodeShouldBe(int arg0) {

        Assertions.assertEquals(arg0,response.statusCode());
    }

    @And("I shoul see {string} message in response body")
    public void iShoulSeeMessageInResponseBody(String arg0) {

        Assertions.assertEquals(response.path("message"),arg0);
    }

    @Then("I update book's author to {string} that has {int} id number")
    public void iUpdateAuthorToThatHasIdNumber(String author, int bookId) {

        Map as = given().accept(ContentType.JSON)
                .header("x-library-token", Hooks.token)
                .when().get("/get_book_by_id/" + bookId)
                .then().statusCode(200).extract().response().as(Map.class);
        Map<String,String> book=new HashMap<>();
        book.putAll(as);
book.replace("author",author);

given().accept(ContentType.JSON)
        .contentType(ContentType.JSON)
      .header("x-library-token", Hooks.token)
        .body(book)
        .when().patch("/update_book")
       .then().statusCode(200);

    }


    @When("I send POST request to {string} with following information")
    public void iSendPOSTRequestToWithFollowingInformation(String endPoint,Map<String,Object> newBook) {
       response = given().accept(ContentType.JSON)
               .header("x-library-token", Hooks.token)
                .formParams(newBook)
                .when().post(endPoint);


    }

    @Then("User should see following parameters")
    public void userShouldSeeFollowingParameters(List<String> editParameters) {
        List<String> param=new ArrayList<>();
        for (WebElement parameter : bookEditPage.parameters) {
            param.add(parameter.getText());
        }
Assertions.assertEquals(editParameters,param);



    }

    @Then("User should verify that Following categories present")
    public void userShouldVerifyThatFollowingCategoriesPresent(List<String> bookCathagories) {
        Select select=new Select(bookEditPage.bookCathagories);
        List<String> param=new ArrayList<>();
        List<WebElement> options = select.getOptions();
        for (WebElement option : options) {
            param.add(option.getText());
        }
        Assertions.assertEquals(bookCathagories,param);


    }

    @When("I send to request {string} endpoint")
    public void iSendToRequestEndpoint(String arg0 ) {

       response = given().accept(ContentType.JSON)
                .header("x-library-token", Hooks.token)
                .when().get(arg0);



    }

    @Then("I should verify that Following categories present and same with database")
    public void iShouldVerifyThatFollowingCategoriesPresent(List<String> list) {
        JsonPath jsonPath = response.jsonPath();
        Assertions.assertEquals(list, jsonPath.getList("name"));
        String query="select * from book_categories";
        List<Object> rowList = DBUtils.getRowList(query);

        System.out.println(rowList.get(0));

    }

    @Then("user should only see all classic books")
    public void userShouldSeeAllClassaicBooks() {


        for (WebElement element : bookEditPage.bookCathegoriName) {
            Assertions.assertEquals(element.getText(),"Classic");
        }

    }

    @And("user select {string} category")
    public void userSelectCategory(String arg0) throws InterruptedException {

        Select select=new Select(bookEditPage.bookCathagories);
        select.selectByVisibleText("Classic");



    }



    @And("User should get searchrecord dropdown to the {string}")
    public void userShouldGetSearchrecordDropdownToThe(String arg0) throws InterruptedException {
        Select select2=new Select(bookEditPage.searchRecordDropdown);
        select2.selectByVisibleText(arg0);
      BrowserUtils.waitFor(3);
    }

    @And("user write in search box {string} as a author name")
    public void userWriteInSearchBoxAsAAuthorName(String arg0) throws InterruptedException {
        bookEditPage.searchBook.sendKeys(arg0);
        BrowserUtils.waitFor(3);
    }



    @Then("user can see {string} author name  on board")
    public void userCanSeeAuthorNameOnBoard(String aouthorName) throws InterruptedException {

            Assertions.assertEquals(bookEditPage.bookauthorName.getText(),aouthorName);


    }

    @Then("Verify the information should be same with database")
    public void verifyTheInformationShouldBeSameWithDatabase() {
        String actualBookName=bookEditPage.bookName.getText();
        String actualauthorName=bookEditPage.bookauthorName.getText();
        String actualBookISBN=bookEditPage.bookIsbn.getText();
        String actualBookyear=bookEditPage.bookYear.getText();


        String query="select * from books where author='Nick Spencer'";
        Map<String, Object> rowMap = DBUtils.getRowMap(query);

        Assertions.assertEquals(rowMap.get("name"),actualBookName);
        Assertions.assertEquals(rowMap.get("isbn"),actualBookISBN);
        Assertions.assertEquals(rowMap.get("year"),actualBookyear);
        Assertions.assertEquals(rowMap.get("author"),actualauthorName);



    }

    @Then("Verify the cathegory information should be same with database")
    public void verifyTheCathegoryInformationShouldBeSameWithDatabase() {



    }
}