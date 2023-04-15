package com.library.stepdefinitions;

import com.library.pages.BooksPage;
import com.library.utilities.BrowserUtils;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

public class BorrowBook_StepDefs {

    BooksPage booksPage = new BooksPage();

    @When("users click the books menu")
    public void users_click_the_books_menu() {
        booksPage.booksBtn.click();
    }

    @Then("success message is displayed")
    public void success_message_is_displayed() {
        BrowserUtils.waitForVisibility(booksPage.successMessage, 10);
        Assert.assertTrue(booksPage.successMessage.isDisplayed());
        Assert.assertEquals("The book has been borrowed...", booksPage.successMessage.getText());

    }

    @And("users enters book {string} into search box")
    public void usersEntersBookDicerosBicornisIntoSearchBox(String bookName) {
        booksPage.searchBox.sendKeys(bookName);
    }

    @And("user click the Barrow Book button")
    public void userClickTheBarrowBookButton() {
        booksPage.borrowBook.click();
    }
}
