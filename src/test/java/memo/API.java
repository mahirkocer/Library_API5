package memo;

import com.library.pages.DataPage;
import com.library.utilities.ConfigurationReader;
import io.restassured.http.ContentType;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.stringContainsInOrder;

public class API {
    @Test
    void test01() {
        baseURI = ConfigurationReader.getProperty("baseUrl");

        DataPage.token = given().accept(ContentType.JSON)
                .contentType("application/x-www-form-urlencoded")
                .formParam("email", ConfigurationReader.getProperty("librarian"))
                .formParam("password", ConfigurationReader.getProperty("password"))
                .post("/login").then().statusCode(200).extract().path("token");
    }

    //TODO day5 hemkrest
//jsonPath.getList("items.findAll {it.region_id==3}.country_name");
    @Test
    void test() {
        String bodytext = given().header("x-library-token", DataPage.token)
                .get("/get_user_by_id/10261").body().asString();

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
}

/**
 * /get_user_by_id/1
 * "/get_all_users"
 * "login"
 */

