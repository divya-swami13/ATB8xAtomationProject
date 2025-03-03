package org.example.APITesting_TestNG;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Example01 {

    @Test(priority = 1)
    public void getToken (){
        System.out.println("1");

    }

    @Test(priority = 2)
    public void getBookingID(){
        System.out.println("2");
    }
@Test(priority = 3)
    public void test_PUT(){
    System.out.println("3");
}

}
