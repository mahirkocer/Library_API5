package com.library.stepdefinitions;

import com.library.pages.Books_BarrowPage;
import com.library.utilities.BrowserUtils;
import com.library.utilities.ConfigurationReader;
import com.library.utilities.Driver;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;

import static org.hamcrest.Matchers.*;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.sql.*;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static javax.swing.UIManager.getInt;

public class BorrowBook_StepDefs {
    WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(5));
    Books_BarrowPage booksBarrowPage = new Books_BarrowPage();
    String book_borrow_id;

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
        js.executeScript("arguments[0].scrollIntoView(true)", booksBarrowPage.unBarrowBtn);
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

        JsonPath jsonPath = given().header("x-library-token", token)
                .formParam("user_id", 515)
                .formParam("book_id", 1282)
                .when().post("/book_borrow").
                then().statusCode(200)
                .extract().jsonPath();
        String mesAct = "The book has been borrowed...";
        String message = jsonPath.getString("message");
        book_borrow_id = jsonPath.getString("book_borrow_id");
        Assertions.assertEquals(mesAct, message);

        Response response = given().header("x-library-token", token).pathParam("id", 10053)
                .when().get("/get_borrowed_books_by_user/{id}")
                .then()
                .statusCode(200).extract().response();

    }

    @Test
    @Then("verify response")
    public void verifyResponse() {

        baseURI = ConfigurationReader.getProperty("baseUrl");
        token = given().accept(ContentType.JSON)
                .contentType("application/x-www-form-urlencoded")
                .formParam("email", ConfigurationReader.getProperty("librarian"))
                .formParam("password", ConfigurationReader.getProperty("password"))
                .post("/login").then().statusCode(200).extract().path("token");


        Map<String, Object> body = new HashMap<>();
        body.put("name", "mahir");
        body.put("isbn", "387122995826");
        body.put("year", "1995");
        body.put("author", "mahsi keder");
        body.put("book_category_id", "5");
        body.put("description", null);


        int id = given().header("x-library-token", token)
                .contentType("application/json")
                .body(body)
                .when().post("/add_book").prettyPeek()
                .then()
                .statusCode(200)
                .body("message", is("The book has been created."))
                .extract().jsonPath().getInt("book_id");

        System.out.println("id = " + id);


        given().header("x-library-token", token)
                .pathParam("id", id)
                .when().get("/get_book_by_id/{id}").prettyPeek()
                .then().statusCode(200)
                .body("name", is("mahir"))
                .body("author", is("mahsi keder"));


    }

    @When("user login as a librarian to api")
    public void userLoginAsALibrarianToApi() {
    }

    @And("user send a request to create a book")
    public void userSendARequestToCreateABook() {
    }

    @When("user navigate to the page")
    public void userNavigateToThePage() {
        Driver.getDriver().get("https://demoqa.com/frames");


    }

    @Then("verify teext")
    public void verifyTeext() {
        Driver.getDriver().switchTo().frame(0);
        WebElement sampleHeading = Driver.getDriver().findElement(By.id("sampleHeading"));
        System.out.println("sampleHeading.getText() = " + sampleHeading.getText());
        Driver.getDriver().switchTo().parentFrame();


    }

    @Then("switch the inner iframe")
    public void switchTheInnerIframe() {
        Driver.getDriver().switchTo().frame("frame2");
        //WebElement frane2 = Driver.getDriver().findElement(By.id("frame2Wrapper"));
        //  Driver.getDriver().switchTo().frame(frane2);
        WebElement sampleHeading = Driver.getDriver().findElement(By.id("sampleHeading"));
        System.out.println("sampleHeading.getText() = " + sampleHeading.getText());


    }
@Test
    @When("user creates a connection and get all user")
    public void userCreatesAConnectionAndGetAllUser() throws SQLException {

        Connection connection = DriverManager.getConnection(ConfigurationReader.getProperty("dbUrl"),ConfigurationReader.getProperty("dbUsername"),ConfigurationReader.getProperty("dbPassword"));
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
        ResultSet resultSet = statement.executeQuery("select full_name,email from users;");

        resultSet.next();
        String string = resultSet.getString(1);
    System.out.println("resultSet.getString(\"email\") = " + resultSet.getString(2));
    System.out.println("string = " + string);

    ResultSetMetaData rsmd= resultSet.getMetaData();
    rsmd.getColumnCount();
    }

}

