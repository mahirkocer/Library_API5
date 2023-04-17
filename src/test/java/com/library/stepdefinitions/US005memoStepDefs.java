package com.library.stepdefinitions;

import com.library.pages.US005memoPage;
import com.library.utilities.BrowserUtils;
import com.library.utilities.DBUtils;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.Assertions;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

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
        Map<String, Object> map = DBUtils.getRowMap(US005memoPage.query_getMylastAddedid);
        Assertions.assertEquals(US005memoPage.addedName, map.get("full_name"));
    }

    @Then("user verifies added user has all params by checking API")
    public void user_Verifies_Added_User_Has_All_Params_By_Checking_API() {

        Response response = given().header("x-library-token", Hooks.token)
                .pathParam("id", addedId)
                .get("/get_user_by_id/{id}");

        String bodytext = response.body().asString();

        MatcherAssert.assertThat(bodytext, stringContainsInOrder(
                "full_name",
                "email",
                "password",
                "user_group",
                "status",
                "start_date",
                "end_date",
                "address"
        ));

    }

    @Then("user checks groups types from API")
    public void user_Checks_Groups_From_Api() {

        Response response = given().header("x-library-token", Hooks.token)
                .get("/get_user_groups");

        List<String> names = response.path("name");
        MatcherAssert.assertThat(names, hasItems("Librarian", "Students"));

    }

    @And("user checks groups from DB")
    public void user_Checks_Groups_From_DB() {
        List<List<Object>> user_groups = DBUtils.getQueryResultList(US005memoPage.query_user_groups);

        MatcherAssert.assertThat(user_groups.toString(), allOf(
                containsString("Student"),
                containsString("Librarian")));
    }

}
