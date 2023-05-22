package com.library.stepdefinitions;

import com.library.pages.BookEditPage;
import com.library.utilities.ApiUtil;
import com.library.utilities.ConfigurationReader;
import com.library.utilities.Driver;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import static io.restassured.RestAssured.*;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import java.util.HashMap;
import java.util.Map;

public class Edit_Book_StepDefs {
    BookEditPage bookEditPage=new BookEditPage();
    Response response;
    @When("user click books")
    public void user_click_books() throws InterruptedException {
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

        Assertions.assertEquals(response.statusCode(),arg0);
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


}