package com.library.stepdefinitions;

import com.library.pages.BookEditPage;
import com.library.utilities.Driver;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;

public class Edit_Book_StepDefs {
    BookEditPage bookEditPage=new BookEditPage();
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
}
