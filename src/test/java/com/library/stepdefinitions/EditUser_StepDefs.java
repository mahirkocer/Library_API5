package com.library.stepdefinitions;

import com.github.javafaker.Faker;
import com.library.pages.LibrarianEditUserPage;
import com.library.utilities.BrowserUtils;
import com.library.utilities.DBUtils;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import static io.restassured.RestAssured.*;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import static org.hamcrest.MatcherAssert.*;

import org.hamcrest.Matchers;
import static org.hamcrest.Matchers.*;
import org.junit.jupiter.api.Assertions;
import static org.junit.jupiter.api.Assertions.*;

public class EditUser_StepDefs {

    LibrarianEditUserPage page = new LibrarianEditUserPage();
    Faker faker = new Faker();
    String usedUserId;
    String usedFullName;
    String usedEmail;

    @Given("user goes to Users page")
    public void user_goes_to_users_page() {
        BrowserUtils.waitForVisibility(page.usersModule, 10);
        page.usersModule.click();
        BrowserUtils.waitForVisibility(page.editUserButton, 10);
    }

    @When("librarian clicks Edit User button")
    public void librarian_clicks_edit_user_button() {
        usedUserId = page.clickRandomEditUserButton();
        BrowserUtils.waitForVisibility(page.fullNameInput, 10);
    }
    @When("librarian updates all the information")
    public void librarian_updates_all_the_information() {
        usedFullName = faker.name().firstName() + " " + faker.name().lastName();
        usedEmail = usedFullName.substring(usedFullName.indexOf(' ')+1) + faker.numerify("###") + "@gmail.com";
        page.editUserFillTheFields(usedFullName, usedEmail);
    }
    @When("librarian clicks Save Changes button")
    public void librarian_clicks_save_changes_button() {
        page.saveChangesButton.click();
        BrowserUtils.waitForVisibility(page.userUpdatedMessage, 10);
        Assertions.assertTrue(page.userUpdatedMessage.isDisplayed());
    }
    @Then("librarian should see the updated version in DB")
    public void librarian_should_see_the_updated_version_in_db() {
        String queryForFullName = "select full_name from users where id = " + usedUserId;
        String queryForEmail = "select email from users where id = " + usedUserId;
        String actualFullName = DBUtils.getCellValue(queryForFullName).toString();
        String actualEmail = DBUtils.getCellValue(queryForEmail).toString();
        assertEquals(usedFullName, actualFullName);
        assertEquals(usedEmail, actualEmail);
    }
    @Then("librarian should see the updated version with all parameters in API")
    public void librarian_should_see_the_updated_version_with_all_parameters_in_apı() {
        Response response = given()
                .header("x-library-token", Hooks.token)
                .pathParam("id", usedUserId)
                .get("/get_user_by_id/{id}");

        assertThat(response.body().asString(), stringContainsInOrder("id","full_name","email",
                "password","user_group_id","status","start_date","end_date","address"));

        JsonPath jsonPath = response.jsonPath();
        String actualFullName = jsonPath.getString("full_name");
        String actualEmail = jsonPath.getString("email");
        assertThat(actualFullName,equalTo(usedFullName));
        assertThat(actualEmail,equalTo(usedEmail));

    }

}
