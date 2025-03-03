package org.example.Integration;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestCaseIntegration {
    RequestSpecification requestSpecification;
    Response response ;
    ValidatableResponse validatableResponse ;

    String token ;
    String bookingId;

    public String getToken(){
        String payload = "\n" +
                "    \"username\" : \"admin\",\n" +
                "    \"password\" : \"password123\"\n" +
                "}";
        requestSpecification = RestAssured.given();
        requestSpecification .baseUri("https://restful-booker.herokuapp.com");
        requestSpecification.basePath("/auth");
        requestSpecification.contentType(ContentType.JSON).log().all();
        requestSpecification.body(payload);

        Response response = requestSpecification.when().post();

        ValidatableResponse validationResponse = response.then();
        validatableResponse.statusCode(200);

        token = response.jsonPath().getString("token");
        System.out.println(token);
        return token;
    }

    private String getBookingID() {
        String payload_POST ="{\n" +
                "    \"firstname\": \"Jim\",\n" +
                "    \"lastname\": \"Brown\",\n" +
                "    \"totalprice\": 622,\n" +
                "    \"depositpaid\": true,\n" +
                "    \"bookingdates\": {\n" +
                "        \"checkin\": \"2017-04-24\",\n" +
                "        \"checkout\": \"2021-08-24\"\n" +
                "    }\n" +
                "}";
        requestSpecification = RestAssured.given();
        requestSpecification .baseUri("https://restful-booker.herokuapp.com");
        requestSpecification.basePath("/auth");
        requestSpecification.contentType(ContentType.JSON).log().all();
        requestSpecification.body(payload_POST).log().all();

        Response response = requestSpecification.when().post();

        validatableResponse  = response.then().log().all();
        validatableResponse.statusCode(200);

        token = response.jsonPath().getString("bookingId");
        System.out.println(bookingId);
        return bookingId;
    }

    @Test(priority = 1)
    public void test_update_request_put(){
        String token = getToken();
        String bookingId = getBookingID();
        System.out.println(token);
        System.out.println(bookingId);
        String payload_PUT = "{\n" +
                "      \"firstname\" : \"Jim\",\n" +
                "      \"lastname\" : \"Brown\",\n" +
                "      \"totalprice\" : 111,\n" +
                "      \"depositpaid\" : true,\n" +
                "     \"bookingdates\" : {\n" +
                "    \"checkin\" : \"2018-01-01\",\n" +
                "   \"checkout\" : \"2019-01-01\"\n" +
                "               },\n" +
                "     \"additionalneeds\" : \"Breakfast\"\n" +
                "}";
        requestSpecification = RestAssured.given();
        requestSpecification .baseUri("https://restful-booker.herokuapp.com");
        requestSpecification.basePath("/auth");
        requestSpecification.contentType(ContentType.JSON).log().all();
        requestSpecification.cookie("token",token);
        Response response = requestSpecification.when().post();

        validatableResponse  = response.then().log().all();
        validatableResponse.statusCode(200);


    }


    @Test(priority = 2)
    public void test_update_request_get() {
        System.out.println(bookingId);
        requestSpecification.baseUri("https://restful-booker.herokuapp.com");
        requestSpecification.basePath("/booking/" + bookingId);
        requestSpecification.when().log().all().get();
        requestSpecification.then().log().all().statusCode(200);

        String firstname = response.jsonPath().getString("firstname");
        Assert.assertEquals(firstname,"jim");
    }
    @Test(priority = 3)
    public void test_delete_booking(){
        System.out.println(bookingId);
        System.out.println(token);
        String payload_PUT = "{\n" +
                "      \"firstname\" : \"Jim\",\n" +
                "      \"lastname\" : \"Brown\",\n" +
                "      \"totalprice\" : 111,\n" +
                "      \"depositpaid\" : true,\n" +
                "     \"bookingdates\" : {\n" +
                "    \"checkin\" : \"2018-01-01\",\n" +
                "   \"checkout\" : \"2019-01-01\"\n" +
                "               },\n" +
                "     \"additionalneeds\" : \"Breakfast\"\n" +
                "}";
        requestSpecification = RestAssured.given();
        requestSpecification .baseUri("https://restful-booker.herokuapp.com");
        requestSpecification.basePath("/auth");
        requestSpecification.contentType(ContentType.JSON).log().all();

        Response response = requestSpecification.when().delete();

        validatableResponse  = response.then().log().all();
        validatableResponse.statusCode(200);

    }
    @Test(priority = 4)
    public void test_after_delete_request_get(){
        requestSpecification.baseUri("https://restful-booker.herokuapp.com");
        requestSpecification.basePath("/booking/" + bookingId);
        requestSpecification.when().log().all().get();
        requestSpecification.then().log().all().statusCode(404);

    }

}
