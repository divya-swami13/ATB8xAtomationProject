package org.example.post;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import jdk.jfr.Description;
import org.testng.annotations.Test;

public class API_BDDStyle {
    @Description("Verify the POST Request- BDD Style")
    @Test
    public void test_post_BDDStyle(){

        String payload = "\n"  +

               "       \"username\" : \"admin\",\n" +
                "     \"username\" : \"password123\"\n" +
                "}";
        RestAssured
                .given()
                .baseUri("https://restful-booker.herokuapp.com")
                .basePath("/auth")
                .contentType(ContentType.JSON)
                .log().all().body(payload)
                .when().post()
                .then().log().all().statusCode(200);
    }
}

