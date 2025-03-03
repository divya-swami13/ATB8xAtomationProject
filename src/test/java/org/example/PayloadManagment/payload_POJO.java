package org.example.PayloadManagment;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.example.Difficultway.Booking;
import org.testng.annotations.Test;

public class payload_POJO {

    RequestSpecification requestSpecification;
    ValidatableResponse validatableResponse;
@Test
    public void testPOSTReq() {

    Booking booking = new Booking();
    booking.setFirstname("pramode");
    booking.setLastname("Dutta");
    booking.setTotalprice(112);
    booking.setDepositpaid(true);

    BookingDates bookingDates = new BookingDates();
    bookingDates.setCheckin("2021-02-01");
    bookingDates.setCheckout("2024-02-01");
    booking.setBookingDates(bookingDates);
    booking.getAdditionalneeds("Breakfast");

    System.out.println(booking);

    requestSpecification = RestAssured.given();
    requestSpecification.baseUri("https://restful-booker.herokuapp.com");
    requestSpecification.basePath("/auth");
    requestSpecification.contentType(ContentType.JSON).log().all();
    requestSpecification.body(booking).log().all();

    Response response = requestSpecification.when().post();

    ValidatableResponse bookingId = response.then().log().all();

    validatableResponse = response.then().log().all();
    validatableResponse.statusCode(200);
            System.out.println("Your Booking id is --> + bookingId");

}
}
