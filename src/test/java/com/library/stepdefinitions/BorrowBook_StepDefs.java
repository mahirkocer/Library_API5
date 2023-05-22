package com.library.stepdefinitions;

import com.library.pages.Books_BarrowPage;
import com.library.utilities.BrowserUtils;
import com.library.utilities.ConfigurationReader;
import com.library.utilities.Driver;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static javax.swing.UIManager.getInt;

public class BorrowBook_StepDefs {
    WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(5));
    Books_BarrowPage booksBarrowPage = new Books_BarrowPage();

    public static String token;

    @When("users click the books menu")
    public void users_click_the_books_menu() {
        BrowserUtils.waitForClickablility(booksBarrowPage.booksBtn, 10);
        booksBarrowPage.booksBtn.click();
    }

    @Then("success message is displayed")
    public void success_message_is_displayed() throws InterruptedException {
        BrowserUtils.waitForVisibility(booksBarrowPage.successMessage, 10);
        Assert.assertTrue(booksBarrowPage.successMessage.isDisplayed());
        Assert.assertEquals("The book has been borrowed...", booksBarrowPage.successMessage.getText());

        booksBarrowPage.BarrowedBoooks.click();
        Thread.sleep(4000);

        JavascriptExecutor js = (JavascriptExecutor) Driver.getDriver();
        js.executeScript("arguments[0].scrollIntoView(true)",booksBarrowPage.unBarrowBtn);
        Thread.sleep(4000);
        booksBarrowPage.unBarrowBtn.click();

    }

//    @And("users enters book {string} into search box")
//    public void usersEntersBookDicerosBicornisIntoSearchBox(String bookName) {
//        booksBarrowPage.searchBox.sendKeys(bookName);
//    }

    @And("user click the Barrow Book button")
    public void userClickTheBarrowBookButton() {
        wait.until(ExpectedConditions.elementToBeClickable(booksBarrowPage.borrowBook));
        booksBarrowPage.borrowBook.click();
    }

    @And("users enters book {string} into seacrh box")
    public void usersEntersBookIntoSeacrhBox(String bookName) {
        booksBarrowPage.searchBox.sendKeys(bookName);
    }
@Test
    @When("user can barrow a book")
    public void userCanBarrowABook() {

        baseURI = ConfigurationReader.getProperty("baseUrl");
        token = given().accept(ContentType.JSON)
                .contentType("application/x-www-form-urlencoded")
                .formParam("email", ConfigurationReader.getProperty("librarian"))
                .formParam("password", ConfigurationReader.getProperty("password"))
                .post("/login").then().statusCode(200).extract().path("token");

  JsonPath jsonPath= given().header("x-library-token", token)
            .formParam("user_id", 515)
            .formParam("book_id", 1282)
            .when().post("/book_borrow").
            then().statusCode(200)
            .extract().jsonPath();
    String mesAct ="The book has been borrowed...";
    String message = jsonPath.getString("message");
    int book_borrow_id = jsonPath.getInt("book_borrow_id");
    Assertions.assertEquals(mesAct,message);

    }

    @And("get barrowed book list")
    public void getBarrowedBookList() {
    }
}

