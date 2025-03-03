package org.example.post;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import jdk.jfr.Description;
import org.testng.annotations.Test;

import static org.example.GET.TEST05_NonBDDStyle.r;

public class API_NonBDDStyle {


    @Description("Verify the POST Request- NonBDD Style")
    @Test
    public void test_post_BDDStyle(){

        String payload = "\n"  +

                "       \"username\" : \"admin\",\n" +
                "     \"username\" : \"password123\"\n" +
                "}";
        RequestSpecification r = RestAssured.given();

            r.baseUri("https://restful-booker.herokuapp.com");
            r.basePath("/auth");
            r.contentType(ContentType.JSON).log().all();
            r.body(payload);
            r.when().post();
            r.then().log().all().statusCode(200);
        }
    }

