package com.library.stepdefinitions;

import com.library.utilities.ConfigurationReader;
import com.library.utilities.DBUtils;
import com.library.utilities.Driver;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.restassured.http.ContentType;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class Hooks {
    public static String token;

    @Before("@api")
    public static void baseURI() {
        baseURI = ConfigurationReader.getProperty("baseUrl");
        token = given().accept(ContentType.JSON)
                .contentType("application/x-www-form-urlencoded")
                .formParam("email", ConfigurationReader.getProperty("librarian"))
                .formParam("password", ConfigurationReader.getProperty("password"))
                .post("/login").then().statusCode(200).extract().path("token");
    }

    @Before("@db")
    public void dbHook() {
        System.out.println("creating database connection");
        DBUtils.createConnection();
    }

    @After("@db")
    public void afterDbHook() {
        System.out.println("closing database connection");
        DBUtils.destroyConnection();
    }

    @Before("@ui")
    public void setUp() {
        // we put a logic that should apply to every scenario
        //    Driver.getDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

    }

    @After()
    public void tearDown(Scenario scenario) {
        // only takes a screenshot if the scenario fails
        if (scenario.isFailed()) {
            // taking a screenshot
            final byte[] screenshot = ((TakesScreenshot) Driver.getDriver()).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", scenario.getName());
        }
        Driver.closeDriver();
    }


}
