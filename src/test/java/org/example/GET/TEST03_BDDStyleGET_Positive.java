package org.example.GET;


import io.restassured.RestAssured;
import org.testng.annotations.Test;
public class TEST03_BDDStyleGET_Positive {
    @Test
    public void test_GET_Req_Positive(){
        RestAssured.
                given()
                .baseUri("https://www.zippopotam.us")
                .basePath("/IN/413006")
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
