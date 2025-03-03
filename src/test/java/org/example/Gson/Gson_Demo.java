package org.example.Gson;

import com.google.gson.Gson;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.example.Difficultway.Booking;
import org.example.PayloadManagment.BookingDates;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;

public class Gson_Demo {
    RequestSpecification requestSpecification;
    ValidatableResponse validatableResponse;

    @Test
    public void testPositives() {
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

        Gson gson = new Gson();
        String JsonStringBooking = gson.toJson(booking);
        System.out.println(JsonStringBooking);
        requestSpecification = RestAssured.given();
        requestSpecification.baseUri("https://restful-booker.herokuapp.com");
        requestSpecification.basePath("/auth");
        requestSpecification.contentType(ContentType.JSON).log().all();
        requestSpecification.body(booking).log().all();

        Response response = requestSpecification.when().post();
        String jsonReaponseString = response.asString();

        validatableResponse = response.then().log().all();
        validatableResponse.statusCode(200);

        BookingResponse bookingResponse = gson.fromJson(jsonReaponseString, BookingResponse.class);

        assertThat(bookingResponse.getBookingid()).isNotEmpty().isNotNull();
        assertThat(bookingResponse.getBooking().getFirstname()).isEqualTo("Pramod").isNotNull().isNotEmpty();
    }


    }


