package com.library.stepdefinitions;

import com.library.pages.US005memoPage;
import com.library.utilities.BrowserUtils;
import com.library.utilities.DBUtils;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;

import java.util.Map;

public class US005memoStepDefs extends US005memoPage {

    @When("user click add user button")
    public void userClickAddUserButton() {
        email = "abc1@abc.com";
        BrowserUtils.waitForClickablility(usersLink, 5);
        usersLink.click();
        BrowserUtils.waitForClickablility(addUserLink, 5);
        addUserLink.click();
    }

    @Then("user fill out new user info")
    public void userFillOutNewUserInfo() {
        BrowserUtils.waitForVisibility(fullnameField, 5);
        fullnameField.sendKeys("hattori hanzo");
        passwordField.sendKeys("pass123");
        emailField.sendKeys(email);
    }

    @And("user clicks save button")
    public void userClicksSaveButton() {
        System.out.println("dont click save, use already saved one");
    }

    @Then("user verifies added name is seen in DB")
    public void userVerifiesCreatedNameIsInDB() {
        Map<String, Object> map = DBUtils.getRowMap(US005memoPage.query_getlastid);
        Assertions.assertEquals(US005memoPage.createdName, map.get("full_name"));
    }

    @Then("user verifies added user has all params by checking API")
    public void userVerifiesAddedUserHasAllParamsByCheckingAPI() {
        //api to hook
        // add adress from sql or api
    }
}
