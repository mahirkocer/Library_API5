package com.library.utilities;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;

public class ApiUtil {
    static String token;

public static String   generateToken(){
    baseURI = ConfigurationReader.getProperty("baseUrl");
  token = given().accept(ContentType.JSON)
            .formParam("email", ConfigurationReader.getProperty("librarian"))
            .formParam("password", ConfigurationReader.getProperty("password"))
            .when().post("/login").then().statusCode(200)
            .and().extract().path("token");
  return token;

}




}
