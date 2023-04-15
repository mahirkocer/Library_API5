package memo;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class API {
    @Test
    void test01() {
        baseURI = Datas.urlApi;

        Datas.token = given().accept(ContentType.JSON)
                .contentType("application/x-www-form-urlencoded")
                .formParam("email", Datas.librarin)
                .formParam("password", Datas.pass)
                .post("/login").then().statusCode(200).extract().path("token");
    }

    //TODO day5 hemkrest
//jsonPath.getList("items.findAll {it.region_id==3}.country_name");
    @Test
    void test() {
        Response response = given().header("x-library-token", Datas.token)
                .get("/get_user_by_id/10261");

        Assertions.assertEquals("mylibDeleteme", response.path("full_name"));
        Assertions.assertEquals("librarian111@library", response.path("email"));
    }
}

/**
 * /get_user_by_id/1
 * "/get_all_users"
 * "login"
 */

