package com.library.stepdefinitions;

import com.library.pages.LoginPage;
import com.library.utilities.BrowserUtils;
import com.library.utilities.ConfigurationReader;
import com.library.utilities.Driver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.openqa.selenium.By;

public class LoginStepDef extends LoginPage {

    @Given("user is on home page")
    public void userIsOnHomePage() {
        Driver.getDriver().get(ConfigurationReader.getProperty("url"));
    }

    @Then("user logs in as {string}")
    public void userLogsInAs(String user) throws InterruptedException {
        emailInput.sendKeys(ConfigurationReader.getProperty(user));
        passwordInput.sendKeys(ConfigurationReader.getProperty("password"));
        signInButton.click();
   BrowserUtils.waitFor(3);
    }
}
