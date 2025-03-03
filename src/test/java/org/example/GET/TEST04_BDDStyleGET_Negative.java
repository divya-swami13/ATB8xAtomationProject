package org.example.GET;

import io.restassured.RestAssured;
import org.testng.annotations.Test;

public class TEST04_BDDStyleGET_Negative {

    @Test
    public void test_GET_Req_Positive(){

        RestAssured.
                given()
                .baseUri("https://www.zippopotam.us")
                .basePath("/IN/-1")
                .when()
                .log()
                .all()
                .get()
                .then()
                .log()
                .all()
                .statusCode(200);
    }
}
