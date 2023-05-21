package com.library.stepdefinitions;

import com.library.pages.Books_BarrowPage;
import com.library.utilities.BrowserUtils;
import com.library.utilities.Driver;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BorrowBook_StepDefs {
    WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(5));
    Books_BarrowPage booksBarrowPage = new Books_BarrowPage();

    @When("users click the books menu")
    public void users_click_the_books_menu() {
        BrowserUtils.waitForClickablility(booksBarrowPage.booksBtn,10);
        booksBarrowPage.booksBtn.click();
    }

    @Then("success message is displayed")
    public void success_message_is_displayed() {
        BrowserUtils.waitForVisibility(booksBarrowPage.successMessage, 10);
        Assert.assertTrue(booksBarrowPage.successMessage.isDisplayed());
        Assert.assertEquals("The book has been borrowed...", booksBarrowPage.successMessage.getText());

        booksBarrowPage.BarrowedBoooks.click();

        JavascriptExecutor js = (JavascriptExecutor) Driver.getDriver();
        js.executeScript("window.scrollBy(0,1000)");

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
    }

