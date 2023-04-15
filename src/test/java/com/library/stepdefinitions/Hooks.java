package com.library.stepdefinitions;

import com.library.utilities.ConfigurationReader;
import com.library.utilities.DBUtils;
import com.library.utilities.Driver;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import static io.restassured.RestAssured.*;

import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.util.concurrent.TimeUnit;


public class Hooks {

	@Before("@api")
	public static void baseURI(){
		baseURI = ConfigurationReader.getProperty("baseUrl");
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
		Driver.getDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

	}
	
	@After
	public void tearDown(Scenario scenario) {
		// only takes a screenshot if the scenario fails
		if (scenario.isFailed()) {
			// taking a screenshot
			final byte[] screenshot = ((TakesScreenshot) Driver.getDriver()).getScreenshotAs(OutputType.BYTES);
			scenario.attach(screenshot, "image/png",scenario.getName());
		}
		//Driver.closeDriver();
	}
	
	
	
	
	
}
