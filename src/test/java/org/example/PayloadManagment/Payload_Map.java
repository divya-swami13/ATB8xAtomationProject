package org.example.PayloadManagment;

import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

import java.util.LinkedHashMap;
import java.util.Map;

public class Payload_Map {

    RequestSpecification requestSpecification;
    ValidatableResponse validatableResponse;

    @Test
    public void testPOSTReq(){
        Map<String,Object >jsonBodyUsingMap = new LinkedHashMap();
        jsonBodyUsingMap.put("firstnmae", "jim");
        jsonBodyUsingMap.put("Lastname","brown");
        jsonBodyUsingMap.put("totalprice",123);
        jsonBodyUsingMap.put("depositpaid",true);

        Map<String,Object>bookingDateMap = new LinkedHashMap();
        bookingDateMap.put("checkin","2018-01-01");
        bookingDateMap.put("checkout","2019-01-01");

        jsonBodyUsingMap.put("bookingdates",bookingDateMap);
        jsonBodyUsingMap.put("additionalneeds","Breakfast");

        System.out.println(jsonBodyUsingMap);
    }
}
