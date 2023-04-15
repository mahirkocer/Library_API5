package com.library.stepdefinitions;

import com.library.pages.US005memoPage;
import com.library.utilities.BrowserUtils;
import com.library.utilities.Driver;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;

public class US005memoStepDefs extends US005memoPage {

    String email;

    @When("user click add user button")
    public void userClickAddUserButton() {
        email = "abc1@abc.com";
        BrowserUtils.waitForClickablility(addNewUserButton,5);
        addNewUserButton.click();
    }

    @Then("user fill out new user info")
    public void userFillOutNewUserInfo() {
        BrowserUtils.waitFor(3);
        Driver.getDriver().findElement(By.cssSelector("a.btn.btn-lg.btn-outline.btn-primary.btn-sm")).click();
        BrowserUtils.waitFor(2);

        Driver.getDriver().findElement(By.xpath("//input[@name='password']")).sendKeys("pass123");
        Driver.getDriver().findElement(By.xpath("//input[@name='email']")).sendKeys(email);
        Driver.getDriver().findElement(By.xpath("//input[@name='full_name']")).sendKeys("hattori hanzo");
    }

    @And("user clicks save button")
    public void userClicksSaveButton() {

    }
}
