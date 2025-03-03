package org.example.sampleCheck;

import io.restassured.RestAssured;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;

public class TEST002 {
    public static void main(String[] args) {

        RestAssured
                    .given()
                    .baseUri("https://restful-booker.herokuapp.com")
                    .basePath("/booking/1")
                    .when()
                    .get()
                    .then()
                    .statusCode(200);
        }
    }

